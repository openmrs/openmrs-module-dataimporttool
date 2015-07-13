package org.esaude.dmt.util.log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Classe que faz a Leitura do ficheiro .properties contendo código e descrição
 * de eventos ERROR e WARNING
 * 
 * @author Edias Jambaia
 * @author Valério João
 * @since 27-08-2014
 */
public class EventCode {
	private final String BUNDLE_NAME = "src/main/resources/eventcode.properties";
	private final Properties props = new Properties();

	public EventCode() {
		try {
			props.load(new FileInputStream(BUNDLE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getString(String key) {
		return props.getProperty(key);
	}
}
