package de.rubenmaurer.punk.util;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Helper for accessing string templates.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class Template {

    /**
     * Loaded template
     */
    private ST template;

    /**
     * Group of available templates.
     */
    private static STGroup templates;

    /**
     * Constructor for a new template.
     *
     * @param template the template to load
     */
    private Template(ST template) {
        this.template = template;
    }

    /**
     * Load a specific template from template pool.
     *
     * @param template the template to load
     * @return the loaded template
     */
    public static Template get(String template) {
        if (ManagementFactory.getRuntimeMXBean().getInputArguments().contains("-Xdebug")) {
            if (templates == null) templates = new STGroupFile("defs.stg");
            return new Template(templates.getInstanceOf(template));
        }

        if (templates == null) templates = new STGroupFile("resources/defs.stg");
        return new Template(templates.getInstanceOf(template));
    }

    /**
     * Get a 'RECV' log message.
     *
     * @param message the message
     * @return the log messaage
     */
    static String recv(String message) {
        return Template.get("DEBUG")
                .single("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .single("type", "RECV")
                .single("message", message).render();
    }

    /**
     * Get a 'SEND' log message.
     *
     * @param message the message
     * @return the log messaage
     */
    static String send(String message) {
        return Template.get("DEBUG")
                .single("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .single("type", "SEND")
                .single("message", message).render();
    }

    /**
     * Get an 'ERROR' log message.
     *
     * @param message the message
     * @return the log messaage
     */
    static String erro(String message) {
        return Template.get("DEBUG")
                .single("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .single("type", "ERROR")
                .single("message", message).render();
    }

    /**
     * Fill a single var in loaded template.
     *
     * @param key the key
     * @param value the value
     * @return the rendered template
     */
    public Template single(String key, String value) {
        template.add(key, value);
        return this;
    }

    /**
     * Render a template without replaced vars.
     *
     * @return the rendered template
     */
    public String render() {
        return template.render();
    }
}

