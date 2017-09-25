package com.seatech.framework.exception;

import com.seatech.framework.utils.Mime2Java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public class PropertiesException {

  private Properties props;


  private String dataFileName, dtdFileName, xslFileName;

  /**
   * Default name for property file
   *
   */
  public static final String propFileName = "ito_exception.properties";

  /**
   * Plattform independent line break.
   *
   */
  public static String lineSep = System.getProperty("line.separator");

  /**
   * Reads a property list from the default file
   *
   * @exception  CustomizeException  if an error occurred when reading from the
   *               file.
   */
  public PropertiesException()  throws CustomizeException {
      this(propFileName);
  }

  /**
   * Reads a property list from a file.
   *
   * @param      propertyFileName
   * @exception  CustomizeException  if an error occurred when reading from the
   * file.
   */
  public PropertiesException(String propertyFileName) throws CustomizeException {
    FileInputStream in;
    props = new Properties();
    try {
      in = new FileInputStream(propertyFileName);
      props.load(in);
      in.close();
    } catch (FileNotFoundException fnfex) {
      if (propFileName.equals(propertyFileName))
	throw new CustomizeException(
	       "Default property file not found!" + lineSep +
	       "Please place a copy of the file " + propFileName + lineSep +
	       "in the working directory: " + System.getProperty("user.dir") + lineSep +
	       "or specify a file name",
	       CustomizeException.PROPERTYFILENOTFOUND);
      else
	throw new CustomizeException(
		       "Property file " + propertyFileName + " not found!",
		       CustomizeException.PROPERTYFILENOTFOUND);
    }
    catch (IOException ioex) {
      throw new CustomizeException(
		       "Could not read property file: " + propertyFileName,
		       CustomizeException.PROPERTYFILEREADERROR);
    }
    //    readAttributes();
  }

  /**
   * Access method for the list of properties
   * @return   the properties object
   */
  public Properties getProperties() {
    return props;
  }

  /**
   * Stores the property list to the specified output stream.
   * @param   out      an output stream.
   */
  public void saveToFile(FileOutputStream pw) {
    props.save(pw, "ttsp_properties v1.0 ");
  }

  /**
     * Searches for the property with the specified key_rpt in the property list.
     * The method returns <code>null</code> if the property is not found.
     *
     * @param key the property key_rpt.
     * @return the value in this property list with the specified key_rpt value.
     */
  public String getProperty(String key) {
    return props.getProperty(key);
  }

  /**
     * Tests if the specified property is in the property list.
     * The method returns <code>true</code> if the property is present.
     *
     * @param key the property key_rpt.
     * @return true if the specified property in the property list;
     * false otherwise.
     */
  public boolean containsKey(String key) {
    return props.containsKey(key);
  }

  /**
     * Searches for the property with the specified key_rpt in the property
     * list. If its value is not <code>null</code> and is equal,
     * ignoring case, to the string
     * <code>"true"</code> the method returns <code>true</code>,
     * and <code>false</code> otherwise.
     *
     * @param key the property key_rpt.
     * @return the <code>"boolean"</code> value in this property list
     */
  public boolean getBooleanProperty(String key) {
    return (new Boolean(getProperty(key))).booleanValue();
  }

  /**
   * Searches for the property to determine whether the specified XML
   * attribute should be used.
   *
   * @param   name   the name of the attribute
   * @return  the <code>"true"</code> if the attribute is in the list
   * and should be used, otherwise <code>"false"</code>.
   */
  public boolean useAttribute(String name) {
    return getBooleanProperty(name);
  }

  /**
   * Changes the usage of the named XML attribute
   *
   * @param   key   the name of the attribute
   * @param   value   usage of attribute
   */
   public void setUseAttribute(String key, boolean value) {
    setBooleanProperty(key, value);
  }

  /**
   * Checks whether the specified property has the given value.
   *
   * @param   key   the name of the property
   * @param   value   value of the property
   */
  public boolean propertyHasValue(String key, String value) {
    if (value == null)
      return false;
    return value.equals(getProperty(key));
  }

 /**
     * Maps the specified <code>key_rpt</code> to the specified
     * <code>value</code> in the property list. Neither the key_rpt nor the
     * value can be <code>null</code>.
     *
     * @param key the property key_rpt
     * @param value the property value
     */
  public void setProperty(String key, String value) {
    props.put(key,value);
  }

 /**
   * Removes a named property from the list.
   *
   * @param   key   the name of the property to be removed
   */
  public void removeProperty(String key) {
    props.remove(key);
  }

 /**
     * Updates or inserts property with specified name. The value will
     * be <code>"true"</code> or <code>"false"</code> (i.e. a string)
     * depending on the parameter.
     *
     * @param key the property key_rpt.
     * @param b the boolean value
     */
  public void setBooleanProperty(String key, boolean b) {
    String value = b ? "true" : "false";
    props.put(key,value);
  }

 /**
   * Get the names of properties dealing with XML element types as
   * an enumeration of Strings.
   *
   * @return enumeration of property names
   */
  public Enumeration getElements() {
     return getPropertiesWithSuffix(".output");
  }

/**
   * Filters out properties with a given prefix.
   *
   * @param prefix  the prefix
   * @return enumeration of matched property names
*/
 protected Enumeration getPropertiesWithSuffix(String suffix) {
    Hashtable h = new Hashtable();
    Enumeration e = props.keys();
    String key;
    while (e.hasMoreElements()) {
      key = (String) e.nextElement();
      if (key.endsWith(suffix))
	h.put(key,props.get(key));
    }
    return h.keys();
  }

  /**
   * Reads a property list from an input stream.
   *
   * @param      in   the input stream.
   * @exception  IOException  if an error occurred when reading from the
   *               input stream.
   */
  public synchronized void loadFromFile(InputStream in) throws IOException {
    props = new Properties();
    props.load(in);
  }

  /**
   * Gets the MIME type for the selected character encoding.
   * The names for the MIME types for character encoding used in
   * Java are not the official names (God knows why). This method
   * checks whether the requested MIME type is available and returns
   * the Java name. If the type is not available it returns <code>UTF8</code>.
   *
   * @see     db2xml.util.Mime2Java
   * @return  name of the mime type of the encoding scheme as used in Java
   */
  public String getJavaEncoding() {
    String enc = getProperty("encoding");
    if (enc == null)
      enc = "UTF-8";
    String javaEnc = Mime2Java.convert(enc);
    if (javaEnc == null)
      javaEnc = "UTF8";
    return javaEnc;
  }

  /**
   * Gets the official MIME type for the selected character encoding.
   * The names for the MIME types for character encoding used in
   * Java are not the official names (God knows why). If the selected
   * MIME is not available <code>UTF-8</code> is returned.
   *
   * @see     db2xml.util.Mime2Java
   * @return  official name of the mime type of the encoding scheme
   */
  public String getMimeEncoding() {
    String enc = getProperty("encoding");
    if (enc == null)
      return "UTF-8";
    if (Mime2Java.convert(enc) == null)
      return "UTF-8";
    return enc;
  }

  /**
   * Access method for filename for DTD
   */
  public String getDtdFileName() {
    return (getDataFileName() == null) ? null :
		(new File(dtdFileName)).getName();
  }

  /**
   * Access method for filename for XML data
   */
  public String getDataFileName() {
    setFileName();
    return dataFileName;
  }

 /**
   * Sets filename for DTD and data (different extensions are used:
   * xml for data and dtd for external DTDs).
   * @param fileName name of outout file
   */
  void setFileName() {
    if (propertyHasValue("out", "file")) {
      dataFileName = getProperty("outFile");
      dtdFileName = getProperty("outDTD");
      xslFileName = getProperty("xslFile");
      if (dtdFileName == null && dataFileName != null) {
	if (dataFileName.length() < 4)
	  dtdFileName =  dataFileName + ".dtd";
	else if ("xml".equals(dataFileName.substring(dataFileName.length()-3)))
	  dtdFileName = dataFileName.substring(0,dataFileName.length()-3) + "dtd";
	else
	  dtdFileName =  dataFileName + ".dtd";
      }
      if (xslFileName == null && dataFileName != null) {
	if (dataFileName.length() < 4)
	  xslFileName =  dataFileName + ".html";
	else if ("xml".equals(dataFileName.substring(dataFileName.length()-3)))
	  xslFileName = dataFileName.substring(0,dataFileName.length()-3) + "html";
	else if (!"html".equals(dataFileName.substring(dataFileName.length()-4)))
	  xslFileName =  dataFileName + ".html";
	else
	  xslFileName =  dataFileName;
      }
    }
  }

 /**
   * Returns the name of the property <em>xslFileName</em>.
   */
  public String getXSLFileName() {
    return (getDataFileName() == null) ? null :
		(new File(xslFileName)).getName();
  }

 /**
   * Do we have to generate a DTD. Property: <em>genDTD</em>
   */
  public boolean genDTD() {
    return !propertyHasValue("genDTD", "no");
  }

 /**
   * Do we have to generate the document. Property: <em>genData</em>
   */
  public boolean genDocument() {
    return getBooleanProperty("genData") ||
		   getBooleanProperty("applyStylesheet");
  }

 /**
   * Check several constraints among the settings. Throws an exception
   * in case of a violation.
   *
   * @exception CustomizeException in case of violation
   */
  public void checkConstraints() throws CustomizeException {
    if (propertyHasValue("genDTD", "extern") &&	propertyHasValue("out", "stout"))
      throw new CustomizeException("You must choose file output in" + " order to have external DTDs!",  CustomizeException.CONSTRAINTVIOLATION);
    if (propertyHasValue("genDTD", "intern") &&	propertyHasValue("genData", "false"))
      throw new CustomizeException("You must choose Generate Data in" + " order to have an internal DTD!",CustomizeException.CONSTRAINTVIOLATION);
    if (propertyHasValue("el.binfields", "extern")) {
      if (!propertyHasValue("out", "file"))
        throw new CustomizeException("You must choose file output in" + " order to store binary fields externally!",CustomizeException.CONSTRAINTVIOLATION);
      if (!getBooleanProperty("at.href"))
        throw new CustomizeException("You must enable the href attribute in" + " order to store binary fields externally!",		     CustomizeException.CONSTRAINTVIOLATION);
    }
  }
}












