package 1553

import chisel3._
import chisel3.util._

class command extends Module{
	val io=IO ( new Bundle{
        val sync =Input(UInt(3.W))
	val term_addr = Input(UInt(5.W))
	val tr = Input(Bool())
	val sub_addr_mc = Input(UInt(5.W))
	val wc_mc = Input(UInt(5.W))
	val parity= Input(Bool())                              
	})

	val cmd_word = RegInit(0.U(20.W))       

	when(io.parity){
		cmd_word := Cat(Seq(io.sync, 0.U(5.W), io.tr, io.sub_addr_mc, 0.U(5.W),parity))
		
	}
	

}



