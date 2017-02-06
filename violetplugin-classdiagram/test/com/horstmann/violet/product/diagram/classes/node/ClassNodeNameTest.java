package com.horstmann.violet.product.diagram.classes.node;

import org.junit.Test;


import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.property.text.LineText;
import com.horstmann.violet.product.diagram.property.text.SingleLineText;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

    public class ClassNodeNameTest {
        @Test
        public void SetNameUpperCaseTest() {
            ClassNode testNode = new ClassNode();
            LineText lt = new SingleLineText();

            String ltText = "className";
            lt.setText(ltText);

            LineText ltResult = new SingleLineText();
            ltResult.setText("className");
            // testNode.setName(lt);
            ClassNode.capitalizeLetter = true;
            testNode.setName(lt);

            //if (ClassNode.capitalizeLetter) {
            assertEquals(ltResult.toString(), testNode.getName().toString());
            //}
        }

        @Test
        public void SetNameLowerCaseTest() {
            ClassNode testNode = new ClassNode();
            LineText lt = new SingleLineText();

            String ltText = "className";
            lt.setText(ltText);
            testNode.setName(lt);

            LineText ltResult = new SingleLineText();
            ltResult.setText("className");

            ClassNode.capitalizeLetter = false;
            //if (!ClassNode.capitalizeLetter) {
            assertEquals(ltResult.toString(), testNode.getName().toString());
            //}
        }

}