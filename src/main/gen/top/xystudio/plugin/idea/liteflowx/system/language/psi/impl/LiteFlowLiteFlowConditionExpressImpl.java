// This is a generated file. Not intended for manual editing.
package top.xystudio.plugin.idea.liteflowx.system.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.*;

public class LiteFlowLiteFlowConditionExpressImpl extends ASTWrapperPsiElement implements LiteFlowLiteFlowConditionExpress {

  public LiteFlowLiteFlowConditionExpressImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LiteFlowVisitor visitor) {
    visitor.visitLiteFlowConditionExpress(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiteFlowVisitor) accept((LiteFlowVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowFinallyExpress getLiteFlowFinallyExpress() {
    return findChildByClass(LiteFlowLiteFlowFinallyExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowPreExpress getLiteFlowPreExpress() {
    return findChildByClass(LiteFlowLiteFlowPreExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowSwitchExpress getLiteFlowSwitchExpress() {
    return findChildByClass(LiteFlowLiteFlowSwitchExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowThenExpress getLiteFlowThenExpress() {
    return findChildByClass(LiteFlowLiteFlowThenExpress.class);
  }

  @Override
  @Nullable
  public LiteFlowLiteFlowWhenExpress getLiteFlowWhenExpress() {
    return findChildByClass(LiteFlowLiteFlowWhenExpress.class);
  }

}
