package top.xystudio.plugin.idea.liteflowx.system.provider;

import com.intellij.ide.util.PsiElementListCellRenderer;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.xystudio.plugin.idea.liteflowx.dom.modal.ComponentElement;
import top.xystudio.plugin.idea.liteflowx.dom.modal.Node;
import top.xystudio.plugin.idea.liteflowx.functionImpl.findNodeImpl;
import top.xystudio.plugin.idea.liteflowx.parse.RegexEntity;
import top.xystudio.plugin.idea.liteflowx.util.LiteFlowUtils;
import top.xystudio.plugin.idea.liteflowx.util.ParseUtils;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

/**
 * 实现对Component的识别以及获取Node的位置
 * @author Coder-XiaoYi
 */
public class XmlComponentToNodeLineMarkerProvider extends XmlLineMarkerProvider{

    @Override
    public Optional<? extends PsiElement[]> apply(@NotNull XmlToken element) {
        DomElement domElement = DomUtil.getDomElement(element);
        if (domElement == null){
            return Optional.empty();
        }
        else if (domElement instanceof ComponentElement){
            String expression = ((ComponentElement) domElement).getValue().getRawText();
            List<RegexEntity> regexEntities = ParseUtils.parseExpression(expression);
            return LiteFlowUtils.findTargetsByRegexEntities(element.getProject(), regexEntities, new findNodeImpl());
        }
        return Optional.empty();
    }

    @Override
    public @NotNull String getTooltip(PsiElement element, @NotNull PsiElement target) {
        String text = "LiteFlow Node found - In " + element.getContainingFile().getVirtualFile().getName();
        if (element instanceof XmlTag){
            DomElement domElement = DomUtil.getDomElement(element);
            if (domElement instanceof Node){
                text = "LiteFlow Node found - (" + ((Node) domElement).getName().getStringValue() +")" + ((Node) domElement).getClazz().getStringValue();
            }
        }
        return text;
    }

    @Override
    public @NotNull Icon getIcon() {
        return LiteFlowIcons.NODE_LINE_MARKER_ICON;
    }

    @Override
    public String getName() {
        return "Node statement line marker";
    }

    @Override
    public PsiElementListCellRenderer getCellRenderer() {
        return new PsiElementListCellRenderer() {
            @Override
            public @NlsSafe String getElementText(PsiElement element) {
                if (!(element instanceof XmlTag)){
                    return null;
                }
                return ((XmlTag) element).getAttributeValue("id");
            }

            @Override
            protected @Nullable
            @NlsSafe String getContainerText(PsiElement element, String name) {
                return element.getContainingFile().getName();
            }

            @Override
            protected int getIconFlags() {
                return 0;
            }

            @Override
            protected Icon getIcon(PsiElement element) {
                return LiteFlowIcons.NODE_LINE_MARKER_ICON;
            }
        };
    }
}
