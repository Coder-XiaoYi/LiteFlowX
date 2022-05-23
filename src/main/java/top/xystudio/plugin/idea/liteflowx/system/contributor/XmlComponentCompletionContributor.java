package top.xystudio.plugin.idea.liteflowx.system.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlToken;
import com.intellij.util.ProcessingContext;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.util.DomUtils;

public class XmlComponentCompletionContributor extends CompletionContributor {

    public XmlComponentCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(XmlToken.class), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiElement position = parameters.getPosition();
                if (!isTargetElement(position)) {
                    return;
                }
                Project project = parameters.getEditor().getProject();

                /** 搜索全部LiteFlowComponent */
                for (PsiClass psiClass : LiteFlowService.getInstance(project).findAllLiteFlowComponent()) {
                    String componentName = LiteFlowService.getInstance(project).getLiteFlowComponentName(psiClass);
                    if (componentName != null){
                        result = result.withPrefixMatcher("ok");
                        result.addElement(
                                JavaLookupElementBuilder.forClass(psiClass, componentName)
                                        .withIcon(LiteFlowIcons.COMPONENT_LINE_MARKER_ICON)
                                        .withTypeText("Component")
                                        .bold()
                        );
                    }

                }
//                /* Find Chain */
//                for (PsiElement element : LiteFlowUtils.findAllLiteFlowChain(project)) {
//                    String text = null;
//                    if (element instanceof XmlTag) {
//                        text = ((XmlTagImpl) element).getAttributeValue("name");
//                    } else if (element instanceof JsonObject) {
//                        JsonStringLiteral chain = Optional.of(JsonUtil.getPropertyValueOfType((JsonObject) element, "name", JsonStringLiteral.class)).orElse(null);
//                        if (chain != null) {
//                            text = chain.getValue();
//                        }
//                    }
//                    if (text != null) {
//                        result.addElement(
//                                LookupElementBuilder.create(element, text).withIcon(Icons.CHAIN_LINE_MARKER_ICON).withTypeText("Chain").bold()
//                        );
//                    }
//                }
//                /* Find Node */
//                for (PsiElement element : LiteFlowUtils.findAllLiteFlowNode(project)) {
//                    String id = null;
//                    String clazz = null;
//                    if (element instanceof XmlTag) {
//                        id = ((XmlTagImpl) element).getAttributeValue("id");
//                        clazz = ((XmlTagImpl) element).getAttributeValue("class");
//                    } else if (element instanceof JsonObject) {
//                        JsonStringLiteral idString = Optional.of(JsonUtil.getPropertyValueOfType((JsonObject) element, "id", JsonStringLiteral.class)).orElse(null);
//                        JsonStringLiteral clazzString = Optional.of(JsonUtil.getPropertyValueOfType((JsonObject) element, "class", JsonStringLiteral.class)).orElse(null);
//                        if (idString != null) {
//                            id = idString.getValue();
//                        }
//                        if (clazzString != null) {
//                            clazz = idString.getValue();
//                        }
//                    }
//                    if (id != null && clazz != null) {
//                        result.addElement(LookupElementBuilder.create(element, id).withIcon(Icons.NODE_LINE_MARKER_ICON).withTypeText("Node").bold());
//                    }
//                }
            }
        });
    }

    private boolean isTargetElement(PsiElement element) {
        if (!(element instanceof XmlToken)){
            return false;
        }
        if (((XmlToken) element).getTokenType() != XmlElementType.XML_ATTRIBUTE_VALUE_TOKEN){
            return false;
        }
        if (DomUtils.isLiteFlowXmlFile(element.getContainingFile())){
            return true;
        }
        return false;
    }
}
