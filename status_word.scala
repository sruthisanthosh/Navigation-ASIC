package 1553

import chisel3._
import chisel3.util._

class status extends Module{
	val io=IO ( new Bundle{
        val sync =Input(UInt(3.W))
	val msg_error = Input(Bool())
	val instr = Input(Bool())
	val ser_req = Input(Bool())
	val bcmd_rd = Input(Bool())
	val busy = Input(Bool())
	val subsys_flag = Input(Bool())
	val dy_bus_accept = Input(Bool())
	val ter_flag= Input(Bool())
	val parity= Input(Bool())                               
	val mismatch = Output(Bool())
	})

	val status_word = RegInit(0.U(32.W)) 
	val mismatch = RegInit(0.U(1.W))
	io.mismatch := mismatch
	mismatch := ~io.parity

	when(io.parity){
		status_word := Cat(Seq(io.sync,0.U(5.W),io.msg_error,io.instr,io.ser_req,0.U(3.W),io.bcmd_rd,io.busy,io.subsys_flag,io.dy_bus_accept,io.ter_flag,io.parity))
        }

}


