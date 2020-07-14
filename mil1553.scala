package 1553

import chisel3._
import chisel3.util._

 class mil1553 extends Module{
	val io=IO ( new Bundle{
    
	})
	val status_word = RegInit(0.U(16.W)) 

	val enm = Module(new encoder(c))
	val dcm = Module(new decoder(c))
    
    val msg_error=dcm.io.parityerror
    val parity = RegInit(false.B)
    val prev_parity=RegInit(false.B)    
    val ser_req =RegInit(false.B)
    val bcmd_rd=dcm.io.dataout.bits(16,12).andR && dcm.io.csw && prev_parity && dcm.io.dataout.bit(10)
    val busy =RegInit(true.B)
    val subsys_flag =RegInit(true.B)
    val ter_flag = RegInit(false.B)

	when(dcm.io.dataout.valid){
		parity := dcm.io.parityerror
		status_word := Cat(Seq(0.U(5.W),msg_error,false.B,ser_req,0.U(3.W),bcmd_rd,busy,subsys_flag,false.B,ter_flag))
        prev_parity :=parity
        }

}





