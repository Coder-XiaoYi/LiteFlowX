package top.xystudio.plugin.idea.liteflowx.system.language.editing;

import com.intellij.codeInsight.editorActions.enter.EnterBetweenBracesDelegate;

public class LiteFlowEnterBetweenBracesDelegate extends EnterBetweenBracesDelegate {
    @Override
    protected boolean isBracePair(char c1, char c2) {
        return (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']') || (c1 == '(' && c2 == ')');
    }
}
