package com.horstmann.violet.workspace.editorpart.behavior;

import com.horstmann.violet.workspace.sidebar.graphtools.IGraphToolsBar;


public class SelectBehavior extends AbstractEditorPartBehavior {

    public SelectBehavior(IGraphToolsBar graphToolsBar) {
        this.graphToolsBar = graphToolsBar;
    }


    /**
     * Select the "Select" as default element on Diagram Tools.
     */
    public void selectItem() {
        graphToolsBar.reset();
    }

    private IGraphToolsBar graphToolsBar;


}
