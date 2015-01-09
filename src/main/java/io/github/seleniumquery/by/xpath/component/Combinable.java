package io.github.seleniumquery.by.xpath.component;

public interface Combinable<T extends XPathComponent> {

    /**
     * Creates a copy of the current component.
     * @return A copy of this component.
     */
    T cloneComponent();

    /**
     * This method clones the current component, clones the component sent as argument and, finally,
     * combines them, returning the combined component, that has the type of the current component.
     * @param other The component that will be cloned and combined to the clone of this current instance.
     * @return A new component, that is the result of the combination of the current instance, plus a clone of the
     * other sent as argument.
     */
    T cloneAndCombineTo(Combinable other);

}