package com.util;

import com.model.DAOModel;
import com.model.FieldModel;

public class BeanUtils {
	public String getMethodName(String name) {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	public String getMethodName(String prefix, String name) {
		return prefix + Character.toUpperCase(name.charAt(0)) + name.substring(1) ;
	}

	private String getFreeSpecs(String dclType, FieldModel field, String keywords){
		
		String rDef = new String();
		
		if(!dclType.equals("@p"))
			rDef += dclType;
		
		if(!field.getName().equals(""))
		  rDef += " " + field.getName(); 
		
		if(!field.getType().equals(""))
		  rDef += " " + getFreeTypeDef(field);
		
		if(!keywords.equals(""))
		  rDef += " " +keywords;
		
		if(!rDef.equals(""))
			rDef +=";";
		
		return rDef;
	}
	
	private String getFreeTypeDef(FieldModel field){
		String rDef = new String();
		
		rDef += field.getType();
		
		if (field.getType().equals("char")||field.getType().equals("int")){
			rDef += "(" + field.getLength() + ")";
		}else if (field.getType().equals("zoned")||field.getType().equals("packed")) {
			rDef += "(" + field.getLength();
			if(!(field.getDecimals().equals("0")||field.getDecimals().equals(""))){
				rDef += ":"+ field.getDecimals();
			}
			rDef += ")";
		}
		return rDef.trim();
	}
	
	private String getSpecs(String specsType, FieldModel field, String type, String keywords) {
		StringBuffer def = new StringBuffer();
		String rDef = new String();

		// inicializa buffer en blanco
		for (int i = 0; i < 75; i++)
			def.append(" ");

		// constante
		def.replace(6, 6, specsType);

		// agrega nombre
		// si es mayor a longitud permitida crea nueva línea
		if (field.getName().length() > 14) {
			def.replace(7, 21, " " + field.getName() + "...");
			rDef = def.toString().trim();
			rDef += "\n";
			def = new StringBuffer();
			for (int i = 0; i < 75; i++)
				def.append(" ");
			def.replace(6, 6, specsType);
		} else {
			def.replace(7, 21, " " + field.getName());
		}

		// agrega tipo
		if (!type.equals("@p")) {
			def.replace(24, 25, type);
		}

		// agrega longitud
		def.replace(35, 39, getRightAjustString(field.getLength(), 5));

		// agrega tipo
		def.replace(40, 40, field.getType());

		// agrega decimals
		if (!field.getType().equals("a"))
			def.replace(41, 42, getRightAjustString(field.getDecimals(), 2));

		// agrega keywords
		def.replace(44, 80, keywords);

		rDef = rDef.concat(def.toString().substring(1));

		// retorna resultado
		return rDef.trim();
	}

	public String getPspecs(String name, String type, String len, String dec, String typeSpec, String keywords) {
		return getSpecs("p", new FieldModel(name, type, len, dec, false), typeSpec, keywords).trim();
	}

	public String getDspecs(String name, String type, String len, String dec, String typeSpec, String keywords) {
		return getSpecs("d", new FieldModel(name, type, len, dec, false), typeSpec, keywords).trim();
	}

	public String getDspecs(FieldModel fm, String typeSpec, String keywords) {
		return getSpecs("d", fm, typeSpec, keywords).trim();
	}
	
	public String getFreePspecs(String name, String keywords) {
		return getFreeSpecs("dcl-proc", new FieldModel(name, "", "", "", false), keywords).trim();
	}
	
	public String getFreeDspecs(String dclType, String name, String type, String len, String dec, String keywords) {
		return getFreeSpecs(dclType, new FieldModel(name, type, len, dec, false), keywords).trim();
	}
	
	public String getFreeDspecs(FieldModel fm, String keywords) {
		return getFreeSpecs("@p", fm, keywords).trim();
	}

	public String getRightAjustString(String base, int finalLen) {
		String strLen = new String();
		if (base.length() < finalLen) {
			for (int i = 0; i < (finalLen - base.length()); i++) {
				strLen += " ";
			}
			strLen += base;
		}
		return strLen;
	}

	public String getComma(DAOModel bm, FieldModel fm, boolean isKey) {
		String rt = new String();
		if (bm.isLastField(fm, isKey)) {
			rt = "";
		} else {
			rt = ",";
		}
		return rt;
	}

	public String getSemiCol(DAOModel bm, FieldModel fm, boolean isKey) {
		String rt = new String();
		if (bm.isLastField(fm, isKey)) {
			rt = ";";
		} else {
			rt = "";
		}
		return rt;
	}

	public String getWhereStmt(DAOModel bm, FieldModel fm, boolean isKey) {
		String rt = new String();
		if (bm.isFirstField(fm, isKey)) {
			rt = "WHERE";
		} else {
			rt = "  AND";
		}
		return rt;
	}
}