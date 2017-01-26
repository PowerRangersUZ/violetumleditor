package com.horstmann.violet.product.diagram.classes.node;

import java.awt.*;

import com.horstmann.violet.framework.graphics.Separator;
import com.horstmann.violet.framework.graphics.content.*;
import com.horstmann.violet.framework.graphics.content.VerticalLayout;
import com.horstmann.violet.framework.graphics.shape.ContentInsideRectangle;
import com.horstmann.violet.product.diagram.classes.ClassDiagramConstant;
import com.horstmann.violet.product.diagram.property.text.decorator.*;
import com.horstmann.violet.product.diagram.common.node.ColorableNode;
import com.horstmann.violet.product.diagram.property.text.LineText;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.property.text.MultiLineText;
import com.horstmann.violet.product.diagram.property.text.SingleLineText;

/**
 * An abstract class node in a class diagram.
 */
public class AbstractClassNode extends ColorableNode
{
    /**
     * Construct an abstract class node with a default size and the text <<abstract>>.
     */
    public AbstractClassNode()
    {
        super();

        name = new SingleLineText(nameConverter);
        methods = new MultiLineText(methodsConverter);
        createContentStructure();
    }

    protected AbstractClassNode(AbstractClassNode node) throws CloneNotSupportedException
    {
        super(node);
        name = node.name.clone();
        methods = node.methods.clone();
        createContentStructure();
    }

    @Override
    protected void beforeReconstruction()
    {
        super.beforeReconstruction();
        if(name == null)
        {
            name = new SingleLineText();
        }
        if(methods == null)
        {
            methods = new MultiLineText();
        }
        name.reconstruction(nameConverter);
        methods.reconstruction(methodsConverter);
    }

    @Override
    protected INode copy() throws CloneNotSupportedException
    {
        return new AbstractClassNode(this);
    }

    @Override
    protected void createContentStructure()
    {
        name.setText(name.toEdit());

        TextContent nameContent = new TextContent(name);
        TextContent methodsContent = new TextContent(methods);

        setTextContentWidthAndHeight(nameContent);

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

    private void setTextContentWidthAndHeight(TextContent nameContent){
        nameContent.setMinHeight(MIN_NAME_HEIGHT);
        nameContent.setMinWidth(MIN_WIDTH);
    }

    @Override
    public void setBorderColor(Color borderColor)
    {
        if(null != separator)
        {
            separator.setColor(borderColor);
        }
        super.setBorderColor(borderColor);
    }

    @Override
    public void setTextColor(Color textColor)
    {
        name.setTextColor(textColor);
        methods.setTextColor(textColor);
    }

    @Override
    public String getToolTip()
    {
        return ClassDiagramConstant.CLASS_DIAGRAM_RESOURCE.getString("tooltip.abstract_class_node");
    }

    /**
     * Sets the name property value.
     *
     * @param newValue the abstract class name
     */
    public void setName(LineText newValue)
    {
        name.setText(newValue);
    }

    /**
     * Gets the name property value.
     *
     * @return the abstract class name
     */
    public LineText getName()
    {
        return name;
    }

    /**
     * Sets the methods property value.
     *
     * @param newValue the methods of this abstract class
     */
    public void setMethods(LineText newValue)
    {
        methods.setText(newValue);
    }

    /**
     * Gets the methods property value.
     *
     * @return the methods of this abstract class
     */
    public LineText getMethods()
    {
        return methods;
    }



    private SingleLineText name;
    private MultiLineText methods;

    private transient Separator separator = null;

    private static final int MIN_NAME_HEIGHT = 45;
    private static final int MIN_WIDTH = 100;
    private static final String STATIC = "<<static>>";

    private static LineText.Converter nameConverter = new LineText.Converter()
    {
        @Override
        public OneLineText toLineString(String text)
        {
            return new PrefixDecorator( new LargeSizeDecorator(new OneLineText(text)), "<center>«abstract»</center>");
        }
    };
    private static final LineText.Converter methodsConverter = new LineText.Converter()
    {
        @Override
        public OneLineText toLineString(String text)
        {
            OneLineText lineString = new OneLineText(text);

            if(lineString.contains(STATIC))
            {
                lineString = new UnderlineDecorator(new RemoveSentenceDecorator(lineString, STATIC));
            }
            return lineString;
        }
    };
}
