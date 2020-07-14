package 1553

import chisel3._
import chisel3.util._
abstract class Params {
	val div : BigInt
	val divisorBits: Int
	val address : LongInt
}

class decoderParams extends Params{
	div = 16
	divisorBit = 6
}


class decoder(c: decoderParams) extends Module{
	val io = IO(new Bundle{
		val div = Input(UInt(c.divisorBits.W))
		val rxvalid = Input(Bool())
		val rxdata = Input(Bool())
		val paritycheck = WireInit(Bool())
		val datavalid = Output(Bool())
		val dataout = ValidIO(0.U(17.W))
		val idata = WireInit(UInt(23.W))
		val parityerror = Output(Bool())
		val csw = Output(Bool())
	})
	
	val counter = RegInit(0.U(c.divisorBits.W))
	val pulse = WireInit(false.B)
	val sampledata = WireInit(false.B)
	val s1 = io.rxdata    
	val s2 = RegNext(s1) /// on each clock cycle the register is updated with the value of argument+
	val s3 = RegNext(s2)
	val data =RegInit(0.U(17.W))
	val majority = (s1 & s3)|(s1 & s2)|(s2 & s3)
	val csw=RegInit(false.B)
	io.csw:=csw

	when(io.rxvalid){
		when(counter === io.div){
			counter := 0.U
			pulse := true.B
			sampledata := false.B
		}.elsewhen(counter === 2.U){
			sampledata := true.B
			pulse := false.B
			counter := counter + 1.U
		}.otherwise{
			counter := counter + 1.U
			sampledata := false.B
			pulse := false.B
		}
	}.otherwise{
		pulse := false.B
		sampledata := false.B
	}

	when(sampledata){
		message := Cat(message(38,0), majority) 
		msglength := msglength + 1.U
	}
 
 
	val message = RegInit(0.U(40.W))
	val msglength = RegInit(0.U())



	val (s_idle::s_command:: s_data::Nil) = Enum(3)
	val state = RegInit(s_idle)


val xnorinter = WireInit(0.U(17.W))
val datainter = WireInit(0.U(17.W))
val buserror = xnorinter.orR

for(i <- 3 to 19){
	xnorinter(i-3) := !(message(2*i) ^ message(2*i + 1))                                              
	datainter(i-3) := message(2*i) & !message(2*i +1)
	
}
when(buserror){
	io.datavalid := false.B
}
otherwise{
	io.datavalid := true.B
}

	switch(state){
		is(s_idle){
			when(msglength === 6.U){
				when(message === 56.U){
					state := s_command
				}.elsewhen(message === 7.U && io.datavalid){
					state := s_data
				}
			}
		}
		is(s_command){ 
            idata = Cat(message(5,0),datainter(16,0))
			cmdata := idata
			csw=true.B

		}
		is(s_data){ 
        
    		paritycheck := data.xorR   //if 0,then data is parity wise correct
			when(paritycheck && rtrx){
				io.parityerror := false.B
				io.dataout.bits := datainter(16,0)
				io.dataout.valid:=true.B
				csw=false.B

			}.otherwise{
				io.parityerror := true.B
				io.dataout.bits := datainter(16,0)
				io.dataout.valid:=true.B
				csw=false.B
			}
		}
    }		
}


