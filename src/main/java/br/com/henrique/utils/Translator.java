package br.com.henrique.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

@Component
public class Translator {

   /*private Locale locale = Locale.getDefault();
   ResourceBundle resourceMessages = ResourceBundle.getBundle("i18n.messages", locale);
   comentado devido a problemas em encontrar o arquivo messages quando executado em container docker
   recupera um locale "en" da openjdk:15 e nao consegue encontrar o arquivo messages
    */
    ResourceBundle resourceMessages = ResourceBundle.getBundle("messages.validation"); 

    public String getText(String messageCode) {
        return resourceMessages.getString(messageCode);  
    }
   
    public String getText(String messageCode, Object... parameters) {
        var text = resourceMessages.getString(messageCode);
        return MessageFormat.format(text, parameters);
    }  

    public boolean containsKey(String messageCode) {
    return resourceMessages.containsKey(messageCode);
    }

}