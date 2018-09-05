package de.rubenmaurer.punk.messages;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.lang.management.ManagementFactory;

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
     * Fill a single var in loaded template.
     *
     * @param key the key
     * @param values the values
     * @return the rendered template
     */
    public Template single(String key, String[] values) {
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append(String.format("%s;", value));
        }

        template.add(key, sb.toString());
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

