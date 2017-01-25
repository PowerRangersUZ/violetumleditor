package com.horstmann.violet.classdiagram.node;

import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.property.text.LineText;
import com.horstmann.violet.product.diagram.property.text.SingleLineText;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcin on 25.01.2017.
 */
public class ClassNodeTest {


    @Test
    public void toBigLetterOn() {
        ClassNode testNode = new ClassNode();
        LineText lineText = new SingleLineText();

        String name = "classTest";
        lineText.setText(name);

        LineText nameResult = new SingleLineText();
        nameResult.setText("ClassTest");

        testNode.setName(lineText);

        testNode.startClassNameFromBig = true;
        testNode.setName(lineText);

        if (testNode.startClassNameFromBig) {
            assertEquals(nameResult.toString(), testNode.getName().toString());
        }
    }

    @Test
    public void toBigLetterOff() {
        ClassNode testClassNode = new ClassNode();
        LineText lineText = new SingleLineText();

        String name = "classTest";
        lineText.setText(name);

        LineText nameResult = new SingleLineText();
        nameResult.setText("classTest");

        testClassNode.setName(lineText);
        ClassNode.startClassNameFromBig = false;
        if (!ClassNode.startClassNameFromBig) {
            assertEquals(nameResult.toString(), testClassNode.getName().toString());
        }
    }

}
