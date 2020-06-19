import chisel3._
import chisel3.util._

class wbmaster extends Module{
  val io = IO(new Bundle{
  		val m_adr_o = Output(0.U(32.W))
		val m_dat_i = Input(0.U(32.W))
		val m_dat_o = Output(0.U(32.W))
		val m_we_o = Output(Bool())
		val m_sel_o = Output(Bool())
		val m_stb_o = Output(Bool())
		val m_ack_i = Input(Bool())
		val m_cyc_o = Output(Bool())
		val m_stall_i = Input(Bool())
		val m_err_i = Input(Bool())
		val m_lock_o = Output(Bool())
		val m_rty_i = Input(Bool())
		//val m_tagn_o = Output(Bool())
		//val m_tagn_i = Output(Bool())
	})
