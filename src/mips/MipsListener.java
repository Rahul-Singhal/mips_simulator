// Generated from Mips.g4 by ANTLR 4.4

  package mips;
  import java.util.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MipsParser}.
 */
public interface MipsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MipsParser#data}.
	 * @param ctx the parse tree
	 */
	void enterData(@NotNull MipsParser.DataContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#data}.
	 * @param ctx the parse tree
	 */
	void exitData(@NotNull MipsParser.DataContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#ascii_var}.
	 * @param ctx the parse tree
	 */
	void enterAscii_var(@NotNull MipsParser.Ascii_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#ascii_var}.
	 * @param ctx the parse tree
	 */
	void exitAscii_var(@NotNull MipsParser.Ascii_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#div_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDiv_stmt(@NotNull MipsParser.Div_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#div_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDiv_stmt(@NotNull MipsParser.Div_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#jal_stmt}.
	 * @param ctx the parse tree
	 */
	void enterJal_stmt(@NotNull MipsParser.Jal_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#jal_stmt}.
	 * @param ctx the parse tree
	 */
	void exitJal_stmt(@NotNull MipsParser.Jal_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#jalr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterJalr_stmt(@NotNull MipsParser.Jalr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#jalr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitJalr_stmt(@NotNull MipsParser.Jalr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#sltiu_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSltiu_stmt(@NotNull MipsParser.Sltiu_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#sltiu_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSltiu_stmt(@NotNull MipsParser.Sltiu_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#srab_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSrab_stmt(@NotNull MipsParser.Srab_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#srab_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSrab_stmt(@NotNull MipsParser.Srab_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#bgt_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBgt_stmt(@NotNull MipsParser.Bgt_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#bgt_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBgt_stmt(@NotNull MipsParser.Bgt_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#jr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterJr_stmt(@NotNull MipsParser.Jr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#jr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitJr_stmt(@NotNull MipsParser.Jr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#asciiz_var}.
	 * @param ctx the parse tree
	 */
	void enterAsciiz_var(@NotNull MipsParser.Asciiz_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#asciiz_var}.
	 * @param ctx the parse tree
	 */
	void exitAsciiz_var(@NotNull MipsParser.Asciiz_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#beq_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBeq_stmt(@NotNull MipsParser.Beq_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#beq_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBeq_stmt(@NotNull MipsParser.Beq_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#exit_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExit_stmt(@NotNull MipsParser.Exit_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#exit_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExit_stmt(@NotNull MipsParser.Exit_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterStmt_list(@NotNull MipsParser.Stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitStmt_list(@NotNull MipsParser.Stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(@NotNull MipsParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(@NotNull MipsParser.TextContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#sb_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSb_stmt(@NotNull MipsParser.Sb_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#sb_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSb_stmt(@NotNull MipsParser.Sb_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#sra_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSra_stmt(@NotNull MipsParser.Sra_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#sra_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSra_stmt(@NotNull MipsParser.Sra_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#label_list}.
	 * @param ctx the parse tree
	 */
	void enterLabel_list(@NotNull MipsParser.Label_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#label_list}.
	 * @param ctx the parse tree
	 */
	void exitLabel_list(@NotNull MipsParser.Label_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#lh_stmt}.
	 * @param ctx the parse tree
	 */
	void enterLh_stmt(@NotNull MipsParser.Lh_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#lh_stmt}.
	 * @param ctx the parse tree
	 */
	void exitLh_stmt(@NotNull MipsParser.Lh_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#xori_stmt}.
	 * @param ctx the parse tree
	 */
	void enterXori_stmt(@NotNull MipsParser.Xori_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#xori_stmt}.
	 * @param ctx the parse tree
	 */
	void exitXori_stmt(@NotNull MipsParser.Xori_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#sh_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSh_stmt(@NotNull MipsParser.Sh_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#sh_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSh_stmt(@NotNull MipsParser.Sh_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#sw_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSw_stmt(@NotNull MipsParser.Sw_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#sw_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSw_stmt(@NotNull MipsParser.Sw_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#j_stmt}.
	 * @param ctx the parse tree
	 */
	void enterJ_stmt(@NotNull MipsParser.J_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#j_stmt}.
	 * @param ctx the parse tree
	 */
	void exitJ_stmt(@NotNull MipsParser.J_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#addiu_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAddiu_stmt(@NotNull MipsParser.Addiu_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#addiu_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAddiu_stmt(@NotNull MipsParser.Addiu_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#ble_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBle_stmt(@NotNull MipsParser.Ble_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#ble_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBle_stmt(@NotNull MipsParser.Ble_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#srl_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSrl_stmt(@NotNull MipsParser.Srl_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#srl_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSrl_stmt(@NotNull MipsParser.Srl_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(@NotNull MipsParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(@NotNull MipsParser.Var_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#lb_stmt}.
	 * @param ctx the parse tree
	 */
	void enterLb_stmt(@NotNull MipsParser.Lb_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#lb_stmt}.
	 * @param ctx the parse tree
	 */
	void exitLb_stmt(@NotNull MipsParser.Lb_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#byte_var}.
	 * @param ctx the parse tree
	 */
	void enterByte_var(@NotNull MipsParser.Byte_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#byte_var}.
	 * @param ctx the parse tree
	 */
	void exitByte_var(@NotNull MipsParser.Byte_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#word_var}.
	 * @param ctx the parse tree
	 */
	void enterWord_var(@NotNull MipsParser.Word_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#word_var}.
	 * @param ctx the parse tree
	 */
	void exitWord_var(@NotNull MipsParser.Word_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#bne_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBne_stmt(@NotNull MipsParser.Bne_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#bne_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBne_stmt(@NotNull MipsParser.Bne_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#la_stmt}.
	 * @param ctx the parse tree
	 */
	void enterLa_stmt(@NotNull MipsParser.La_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#la_stmt}.
	 * @param ctx the parse tree
	 */
	void exitLa_stmt(@NotNull MipsParser.La_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#int_list}.
	 * @param ctx the parse tree
	 */
	void enterInt_list(@NotNull MipsParser.Int_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#int_list}.
	 * @param ctx the parse tree
	 */
	void exitInt_list(@NotNull MipsParser.Int_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#space_var}.
	 * @param ctx the parse tree
	 */
	void enterSpace_var(@NotNull MipsParser.Space_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#space_var}.
	 * @param ctx the parse tree
	 */
	void exitSpace_var(@NotNull MipsParser.Space_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#ori_stmt}.
	 * @param ctx the parse tree
	 */
	void enterOri_stmt(@NotNull MipsParser.Ori_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#ori_stmt}.
	 * @param ctx the parse tree
	 */
	void exitOri_stmt(@NotNull MipsParser.Ori_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#andi_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAndi_stmt(@NotNull MipsParser.Andi_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#andi_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAndi_stmt(@NotNull MipsParser.Andi_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#blt_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBlt_stmt(@NotNull MipsParser.Blt_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#blt_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBlt_stmt(@NotNull MipsParser.Blt_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#var_list}.
	 * @param ctx the parse tree
	 */
	void enterVar_list(@NotNull MipsParser.Var_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#var_list}.
	 * @param ctx the parse tree
	 */
	void exitVar_list(@NotNull MipsParser.Var_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#byte_list}.
	 * @param ctx the parse tree
	 */
	void enterByte_list(@NotNull MipsParser.Byte_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#byte_list}.
	 * @param ctx the parse tree
	 */
	void exitByte_list(@NotNull MipsParser.Byte_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#addi_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAddi_stmt(@NotNull MipsParser.Addi_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#addi_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAddi_stmt(@NotNull MipsParser.Addi_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#sll_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSll_stmt(@NotNull MipsParser.Sll_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#sll_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSll_stmt(@NotNull MipsParser.Sll_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#lhu_stmt}.
	 * @param ctx the parse tree
	 */
	void enterLhu_stmt(@NotNull MipsParser.Lhu_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#lhu_stmt}.
	 * @param ctx the parse tree
	 */
	void exitLhu_stmt(@NotNull MipsParser.Lhu_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#var_init}.
	 * @param ctx the parse tree
	 */
	void enterVar_init(@NotNull MipsParser.Var_initContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#var_init}.
	 * @param ctx the parse tree
	 */
	void exitVar_init(@NotNull MipsParser.Var_initContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#addu_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAddu_stmt(@NotNull MipsParser.Addu_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#addu_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAddu_stmt(@NotNull MipsParser.Addu_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#move_stmt}.
	 * @param ctx the parse tree
	 */
	void enterMove_stmt(@NotNull MipsParser.Move_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#move_stmt}.
	 * @param ctx the parse tree
	 */
	void exitMove_stmt(@NotNull MipsParser.Move_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#lw_stmt}.
	 * @param ctx the parse tree
	 */
	void enterLw_stmt(@NotNull MipsParser.Lw_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#lw_stmt}.
	 * @param ctx the parse tree
	 */
	void exitLw_stmt(@NotNull MipsParser.Lw_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#srlb_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSrlb_stmt(@NotNull MipsParser.Srlb_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#srlb_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSrlb_stmt(@NotNull MipsParser.Srlb_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#and_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAnd_stmt(@NotNull MipsParser.And_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#and_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAnd_stmt(@NotNull MipsParser.And_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#mult_stmt}.
	 * @param ctx the parse tree
	 */
	void enterMult_stmt(@NotNull MipsParser.Mult_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#mult_stmt}.
	 * @param ctx the parse tree
	 */
	void exitMult_stmt(@NotNull MipsParser.Mult_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#slt_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSlt_stmt(@NotNull MipsParser.Slt_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#slt_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSlt_stmt(@NotNull MipsParser.Slt_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#or_stmt}.
	 * @param ctx the parse tree
	 */
	void enterOr_stmt(@NotNull MipsParser.Or_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#or_stmt}.
	 * @param ctx the parse tree
	 */
	void exitOr_stmt(@NotNull MipsParser.Or_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#sltu_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSltu_stmt(@NotNull MipsParser.Sltu_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#sltu_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSltu_stmt(@NotNull MipsParser.Sltu_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(@NotNull MipsParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(@NotNull MipsParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#sub_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSub_stmt(@NotNull MipsParser.Sub_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#sub_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSub_stmt(@NotNull MipsParser.Sub_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull MipsParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull MipsParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#divu_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDivu_stmt(@NotNull MipsParser.Divu_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#divu_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDivu_stmt(@NotNull MipsParser.Divu_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#add_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAdd_stmt(@NotNull MipsParser.Add_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#add_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAdd_stmt(@NotNull MipsParser.Add_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#sllb_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSllb_stmt(@NotNull MipsParser.Sllb_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#sllb_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSllb_stmt(@NotNull MipsParser.Sllb_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#lbu_stmt}.
	 * @param ctx the parse tree
	 */
	void enterLbu_stmt(@NotNull MipsParser.Lbu_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#lbu_stmt}.
	 * @param ctx the parse tree
	 */
	void exitLbu_stmt(@NotNull MipsParser.Lbu_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#lui_stmt}.
	 * @param ctx the parse tree
	 */
	void enterLui_stmt(@NotNull MipsParser.Lui_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#lui_stmt}.
	 * @param ctx the parse tree
	 */
	void exitLui_stmt(@NotNull MipsParser.Lui_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#subu_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSubu_stmt(@NotNull MipsParser.Subu_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#subu_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSubu_stmt(@NotNull MipsParser.Subu_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#slti_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSlti_stmt(@NotNull MipsParser.Slti_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#slti_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSlti_stmt(@NotNull MipsParser.Slti_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#li_stmt}.
	 * @param ctx the parse tree
	 */
	void enterLi_stmt(@NotNull MipsParser.Li_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#li_stmt}.
	 * @param ctx the parse tree
	 */
	void exitLi_stmt(@NotNull MipsParser.Li_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#xor_stmt}.
	 * @param ctx the parse tree
	 */
	void enterXor_stmt(@NotNull MipsParser.Xor_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#xor_stmt}.
	 * @param ctx the parse tree
	 */
	void exitXor_stmt(@NotNull MipsParser.Xor_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#multu_stmt}.
	 * @param ctx the parse tree
	 */
	void enterMultu_stmt(@NotNull MipsParser.Multu_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#multu_stmt}.
	 * @param ctx the parse tree
	 */
	void exitMultu_stmt(@NotNull MipsParser.Multu_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#nor_stmt}.
	 * @param ctx the parse tree
	 */
	void enterNor_stmt(@NotNull MipsParser.Nor_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#nor_stmt}.
	 * @param ctx the parse tree
	 */
	void exitNor_stmt(@NotNull MipsParser.Nor_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#bge_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBge_stmt(@NotNull MipsParser.Bge_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#bge_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBge_stmt(@NotNull MipsParser.Bge_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MipsParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull MipsParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MipsParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull MipsParser.StmtContext ctx);
}