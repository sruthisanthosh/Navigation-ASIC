package encdec

import chisel3._
import chisel3.util._

class status extends Module{
	val io=IO ( new Bundle{
        val addr =Input(UInt(3.W))
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
}

val status_word = RegInit(0.U(32.W))
val checkparity= paritycheck := data.xorR                 

when(checkparity === parity){
	status_word := Cat(seq(address,0.U(5.W),msg_error,instr,ser_req,0.U(3.W),bcmd_rd,busy,subsys_flag,dy_bus_accept,ter_flag,parity))
	mismatch := False.B
}
otherwise{
	mismatch := True.B
}



