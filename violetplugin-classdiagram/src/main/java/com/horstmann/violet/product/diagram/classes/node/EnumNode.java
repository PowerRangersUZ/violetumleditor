package com.horstmann.violet.product.diagram.classes.node;

import com.horstmann.violet.framework.graphics.Separator;
import com.horstmann.violet.framework.graphics.content.*;
import com.horstmann.violet.framework.graphics.shape.ContentInsideRectangle;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.ClassDiagramConstant;
import com.horstmann.violet.product.diagram.common.node.ColorableNode;
import com.horstmann.violet.product.diagram.common.node.PointNode;
import com.horstmann.violet.product.diagram.property.text.LineText;
import com.horstmann.violet.product.diagram.property.text.MultiLineText;
import com.horstmann.violet.product.diagram.property.text.SingleLineText;
import com.horstmann.violet.product.diagram.property.text.decorator.*;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An interface node in a class diagram.
 */
public class EnumNode extends ColorableNode {
    /**
     * Construct an interface node with a default size and the text <<interface>>.
     */
    public EnumNode() {
        super();
        name = new SingleLineText(nameConverter);
        values = new MultiLineText(valuesConverter);
        createContentStructure();
    }

    protected EnumNode(EnumNode node) throws CloneNotSupportedException {
        super(node);
        name = node.name.clone();
        values = node.values.clone();
        createContentStructure();
    }

    @Override
    protected void beforeReconstruction() {
        super.beforeReconstruction();
        if (null == name) {
            name = new SingleLineText();
        }
        if (null == values) {
            values = new MultiLineText();
        }
        name.reconstruction(nameConverter);
        values.reconstruction(valuesConverter);
    }

    @Override
    protected INode copy() throws CloneNotSupportedException {
        return new EnumNode(this);
    }

    @Override
    protected void createContentStructure() {
        name.setText(name.toEdit());

        TextContent nameContent = new TextContent(name);
        nameContent.setMinHeight(MIN_NAME_HEIGHT);
        nameContent.setMinWidth(MIN_WIDTH);
        TextContent methodsContent = new TextContent(values);

        VerticalLayout verticalGroupContent = new VerticalLayout();
        verticalGroupContent.add(nameContent);
        verticalGroupContent.add(methodsContent);
        separator = new Separator.LineSeparator(getBorderColor());
        verticalGroupContent.setSeparator(separator);

        ContentInsideShape contentInsideShape = new ContentInsideRectangle(verticalGroupContent);

        setBorder(new ContentBorder(contentInsideShape, getBorderColor()));
        setBackground(new ContentBackground(getBorder(), getBackgroundColor()));
        setContent(getBackground());
    }

    @Override
    public void setBorderColor(Color borderColor) {
        if (null != separator) {
            separator.setColor(borderColor);
        }
        super.setBorderColor(borderColor);
    }

    @Override
    public void setTextColor(Color textColor) {
        name.setTextColor(textColor);
        values.setTextColor(textColor);
    }

    @Override
    public String getToolTip() {
        return ClassDiagramConstant.CLASS_DIAGRAM_RESOURCE.getString("tooltip.enum_node");
    }

    @Override
    public boolean addChild(INode node, Point2D point) {
        if (node instanceof PointNode) {
            return true;
        }
        return false;
    }

    /**
     * Sets the name property value.
     *
     * @param newValue the interface name
     */
    public void setName(LineText newValue) {
        name.setText(newValue);
    }

    /**
     * Gets the name property value.
     *
     * @return the interface name
     */
    public LineText getName() {
        return name;
    }

    /**
     * Sets the values property value.
     *
     * @param newValue the values of this interface
     */
    public void setValues(LineText newValue) {
        values.setText(newValue);
    }

    /**
     * Gets the values property value.
     *
     * @return the values of this interface
     */
    public LineText getValues() {
        return values;
    }


    private SingleLineText name;
    private MultiLineText values;

    private transient Separator separator = null;

    private static final int MIN_NAME_HEIGHT = 45;
    private static final int MIN_WIDTH = 100;
    private static final String STATIC = "<<static>>";

    private static LineText.Converter nameConverter = new LineText.Converter() {
        @Override
        public OneLineText toLineString(String text) {
            return new PrefixDecorator(new LargeSizeDecorator(new OneLineText(text)), "<center>«enum»</center>");
        }
    };
    private static final LineText.Converter valuesConverter = new LineText.Converter() {
        @Override
        public OneLineText toLineString(String text) {
            OneLineText lineString = new OneLineText(text);

            if (lineString.contains(STATIC)) {
                lineString = new UnderlineDecorator(new RemoveSentenceDecorator(lineString, STATIC));
            }
            return lineString;
        }
    };
}
