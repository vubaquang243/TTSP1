package com.seatech.ttsp.user;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class UserHandler extends DefaultHandler {

    private String strCurrentElement;
    private String strValue;


    private ArrayList lstUser = null;
    private UserVO userVO = null;

    public List getUser() {
        return lstUser;
    }

    public UserHandler() {
        lstUser = new ArrayList();
    }

    public void characters(char[] ch, int start,
                           int length) throws SAXException {
        strValue = (new String(ch, start, length)).trim();
        if (strCurrentElement.equalsIgnoreCase("displayName")) {
            userVO.setMa_nsd(strValue);
        }
    }

    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if (localName.equalsIgnoreCase("LDAPSearchResult")) {
            if (userVO != null)
                lstUser.add(userVO);
        }
    }

    public void startDocument() throws SAXException {
        super.startDocument();
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        if (localName.equalsIgnoreCase("LDAPSearchResult")) {
            if (userVO != null) {
                lstUser.add(userVO);
            }
            userVO = new UserVO();
        }
        strCurrentElement = localName;
    }
}

