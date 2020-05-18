package 1553

import chisel3._

abstract class EncParams {
	val div : BigInt
	val divisorBits: Int
}

class encoderParams extends EncParams{
	div = 32
	divisorBits = 6
}


class encoder extends Module{
	val io = IO (new Bundle {
	val tx_dword =Input(UInt(16.W))
	val tx_valid = Input(Bool())
    val tx_csw = Input(Bool())
    val tx_dw = Input(Bool())
    val tx_data = Output(UInt(20.W))
    val tx_dval = Output(Bool())
    val tx_busy = Output(Bool())
	})   

val counter = RegInit(0.U(c.divisorBits.W))
	//val pulse = WireInit(false.B) 
	val sampledata = WireInit(false.B)
	val s1 = io.tx_dword  
	val s2 = RegNext(s1) /// on each clock cycle the register is updated with the value of argument
	val s3 = RegNext(s2)
	val majority = (s1 & s3)|(s1 & s2)|(s2 & s3)
	when(io.tx_valid && !(io.tx_busy)){ 
		when(counter === io.div){
			counter := 0.U
			//pulse := true.B
			sampledata := false.B
		}.elsewhen(counter === 2.U){
			sampledata := true.B
			//pulse := false.B
			counter := counter + 1.U
		}.otherwise{
			counter := counter + 1.U
			sampledata := false.B
			//pulse := false.B
		}
	}.otherwise{
		//pulse := false.B
		sampledata := false.B
	}

	when(sampledata){
		datain:= Cat(datain(14,0), majority)   //data before encoding
		//msglength := msglength + 1.U
	}
    val datain = RegInit(0.U(16.W)) 
    val parity = WireInit(0.U(1.W))
    parity := !(datain.xorR)
    val datawparity = RegInit(0.U(17.W)) //data concatenated with parity
	datawparity := Cat(datain,parity)
	//val msglength = RegInit(0.U())

   val interdata = regInit(0.U(32.W))

   interdata := Mux(datawparity,3.U(2.W),1.U(2.W))  // "b10".U ,"b01".U 
                                                    //data after encoding 
   val sync = RegInit(0.U(6.W))

	when(io.tx_csw)  //checking if the data input is either command or status
	{
		sync := 56.U
		io.tx_data := Cat(sync,interdata)
		io.tx_dval := True.B
		io.tx_busy := True.B
	}
	.elsewhen(io.tx_dw){ //checking if it is a data word that is input
		sync := 7.U
		io.tx_data := Cat(sync,interdata)
		io.tx_dval := True.B
		io.tx_busy := True.B
	}
	.otherwise{  //for all other conditions
		io.tx_data := DontCare
		io.tx_dval := False.B
		io.tx_busy := False.B

	 	}
    }
}

