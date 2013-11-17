/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.log.Log;

/**
 * @author 王华
 * @version 2013年11月17日 下午8:37:59
 * 
 */
public class TemplateEngine implements RuntimeConstants {
    /**
     * The Runtime logger.
     */
    private Log log = new Log();
    /**
     * These are the properties that are laid down over top of the default
     * properties when requested.
     */
    private ExtendedProperties overridingProperties = new ExtendedProperties();
    private TemplateLoader templateLoader;

    /**
     * Init-less CTOR
     */
    public TemplateEngine() {
        // do nothing
    }

    /**
     * Construct a TemplateEngine with the initial properties.
     */
    public TemplateEngine(String propsFilename) {
        init(propsFilename);
    }

    /**
     * Construct a TemplateEngine with the specified initial properties.
     */
    public TemplateEngine(Properties props) {
        init(props);

    }

    /**
     * initialize the TemplateEngine, using properties file
     * 
     * @param propsFilename file containing properties to use to initialize
     */
    public void init(String propsFilename) {
        try {
            this.overridingProperties = new ExtendedProperties(propsFilename);
        } catch (IOException e) {
            throw new RuntimeException("Error reading properties from '" + propsFilename + "'", e);
        }
    }

    /**
     * initialize the TemplateEngine, using properties file
     * 
     * @param p Proprties object containing initialization properties
     */
    public void init(Properties p) {
        Enumeration en = p.keys();
        while (en.hasMoreElements()) {
            String key = en.nextElement().toString();
            setProperty(key, p.get(key));
        }
    }

    /**
     * Set Runtime property.
     * 
     * @param key property key
     * @param value property value
     */
    public void setProperty(String key, Object value) {
        this.overridingProperties.setProperty(key, value);
    }

    /**
     * Add a Velocity Runtime property.
     * 
     * @param key
     * @param value
     */
    public void addProperty(String key, Object value) {
        this.overridingProperties.addProperty(key, value);
    }

    /**
     * Clear a Velocity Runtime property.
     * 
     * @param key of property to clear
     */
    public void clearProperty(String key) {
        this.overridingProperties.clearProperty(key);
    }

    /**
     * Set an entire configuration at once. This is useful in cases where the
     * parent application uses the ExtendedProperties class and the velocity
     * configuration is a subset of the parent application's configuration.
     * 
     * @param configuration
     * 
     */
    public void setExtendedProperties(ExtendedProperties configuration) {
        this.overridingProperties = configuration;
    }

    /**
     * Get a Velocity Runtime property.
     * 
     * @param key property to retrieve
     * @return property value or null if the property not currently set
     */
    public Object getProperty(String key) {
        return this.overridingProperties.getProperty(key);
    }

    /**
     * renders the input string using the context into the output writer. To be
     * used when a template is dynamically constructed, or want to use Velocity
     * as a token replacer.
     * 
     * @param context context to use in rendering input string
     * @param out Writer in which to render the output
     * @param logTag string to be used as the template name for log messages in
     *            case of error
     * @param instring input string containing the VTL to be rendered
     * 
     * @return true if successful, false otherwise. If false, see Velocity
     *         runtime log
     * @throws ParseErrorException The template could not be parsed.
     * @throws MethodInvocationException A method on a context object could not
     *             be invoked.
     * @throws ResourceNotFoundException A referenced resource could not be
     *             loaded.
     */
    public boolean evaluate(Context context, Writer out, String logTag, String instring) throws ParseErrorException,
            MethodInvocationException, ResourceNotFoundException {
        return ri.evaluate(context, out, logTag, instring);
    }

    /**
     * Renders the input stream using the context into the output writer. To be
     * used when a template is dynamically constructed, or want to use Velocity
     * as a token replacer.
     * 
     * @param context context to use in rendering input string
     * @param writer Writer in which to render the output
     * @param logTag string to be used as the template name for log messages in
     *            case of error
     * @param instream input stream containing the VTL to be rendered
     * 
     * @return true if successful, false otherwise. If false, see Velocity
     *         runtime log
     * @throws ParseErrorException
     * @throws MethodInvocationException
     * @throws ResourceNotFoundException
     * @throws IOException
     * @deprecated Use
     *             {@link #evaluate(Context context, Writer writer, String logTag, Reader reader ) }
     */
    public boolean evaluate(Context context, Writer writer, String logTag, InputStream instream) throws ParseErrorException,
            MethodInvocationException, ResourceNotFoundException, IOException {
        /*
         * first, parse - convert ParseException if thrown
         */
        BufferedReader br = null;
        String encoding = null;

        try {
            encoding = ri.getString(INPUT_ENCODING, ENCODING_DEFAULT);
            br = new BufferedReader(new InputStreamReader(instream, encoding));
        } catch (UnsupportedEncodingException uce) {
            String msg = "Unsupported input encoding : " + encoding + " for template " + logTag;
            throw new ParseErrorException(msg);
        }

        return evaluate(context, writer, logTag, br);
    }

    /**
     * Renders the input reader using the context into the output writer. To be
     * used when a template is dynamically constructed, or want to use Velocity
     * as a token replacer.
     * 
     * @param context context to use in rendering input string
     * @param writer Writer in which to render the output
     * @param logTag string to be used as the template name for log messages in
     *            case of error
     * @param reader Reader containing the VTL to be rendered
     * 
     * @return true if successful, false otherwise. If false, see Velocity
     *         runtime log
     * @throws ParseErrorException The template could not be parsed.
     * @throws MethodInvocationException A method on a context object could not
     *             be invoked.
     * @throws ResourceNotFoundException A referenced resource could not be
     *             loaded.
     * @since Velocity v1.1
     */
    public boolean evaluate(Context context, Writer writer, String logTag, Reader reader) throws ParseErrorException,
            MethodInvocationException, ResourceNotFoundException {
        return ri.evaluate(context, writer, logTag, reader);
    }

    /**
     * Invokes a currently registered Velocimacro with the params provided and
     * places the rendered stream into the writer. <br>
     * Note : currently only accepts args to the VM if they are in the context.
     * 
     * @param vmName name of Velocimacro to call
     * @param logTag string to be used for template name in case of error. if
     *            null, the vmName will be used
     * @param params keys for args used to invoke Velocimacro, in java format
     *            rather than VTL (eg "foo" or "bar" rather than "$foo" or
     *            "$bar")
     * @param context Context object containing data/objects used for rendering.
     * @param writer Writer for output stream
     * @return true if Velocimacro exists and successfully invoked, false
     *         otherwise.
     */
    public boolean invokeVelocimacro(String vmName, String logTag, String params[], Context context, Writer writer) {
        return ri.invokeVelocimacro(vmName, logTag, params, context, writer);
    }

    /**
     * Merges a template and puts the rendered stream into the writer. The
     * default encoding that Velocity uses to read template files is defined in
     * the property input.encoding and defaults to ISO-8859-1.
     * 
     * @param templateName name of template to be used in merge
     * @param context filled context to be used in merge
     * @param writer writer to write template into
     * 
     * @return true if successful, false otherwise. Errors logged to velocity
     *         log.
     * @throws ResourceNotFoundException
     * @throws ParseErrorException
     * @throws MethodInvocationException
     * @deprecated Use
     *             {@link #mergeTemplate(String templateName, String encoding, Context context, Writer writer )}
     */
    public boolean mergeTemplate(String templateName, Context context, Writer writer) throws ResourceNotFoundException,
            ParseErrorException, MethodInvocationException {
        return mergeTemplate(templateName, ri.getString(INPUT_ENCODING, ENCODING_DEFAULT), context, writer);
    }

    /**
     * merges a template and puts the rendered stream into the writer
     * 
     * @param templateName name of template to be used in merge
     * @param encoding encoding used in template
     * @param context filled context to be used in merge
     * @param writer writer to write template into
     * 
     * @return true if successful, false otherwise. Errors logged to velocity
     *         log
     * @throws ResourceNotFoundException
     * @throws ParseErrorException
     * @throws MethodInvocationException
     * @since Velocity v1.1
     */
    public boolean mergeTemplate(String templateName, String encoding, Context context, Writer writer)
            throws ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        Template template = ri.getTemplate(templateName, encoding);

        if (template == null) {
            String msg = "TemplateEngine.mergeTemplate() was unable to load template '" + templateName + "'";
            getLog().error(msg);
            throw new ResourceNotFoundException(msg);
        } else {
            template.merge(context, writer);
            return true;
        }
    }

    public Template getTemplate(String name) throws ResourceNotFoundException, ParseErrorException {
        return templateLoader.getTemplate(name);
    }
}
