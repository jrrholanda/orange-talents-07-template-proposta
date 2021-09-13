package br.com.zup.propostas.utils;

import org.apache.commons.codec.binary.Base64;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class Criptografia implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        Base64 base64 = new Base64();
        String encodedVersion = new String(base64.encode(attribute.getBytes()));

        return encodedVersion;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        Base64 base64 = new Base64();
        String decodedVersion = new String(base64.decode(dbData.getBytes()));

        return decodedVersion;
    }
}
