import chisel3._
import chisel3.util._

class wbslave extends Module{
  val io = IO(new Bundle{
  		val s_adr_i = Input(0.U(32.W))
		val s_dat_i = Input(0.U(32.W))
		val s_dat_o = Output(0.U(32.W))
		val s_we_i = Input(Bool())
		val s_sel_i = Input(Bool())
		val s_stb_i = Input(Bool())
		val s_ack_o = Output(Bool())
		val s_cyc_i = Input(Bool())
		val s_stall_o = Ouput(Bool())
		val s_err_o = Ouput(Bool())
		val s_lock_i = Input(Bool())
		val s_rty_o = Output(Bool())
		//val s_tagn_i = Output(Bool())
		//val s_tagn_o = Output(Bool())
	})
