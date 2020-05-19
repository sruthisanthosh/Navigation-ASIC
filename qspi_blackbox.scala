package 1553_ED
import chisel3._
import chisel3.util._

class BlackBoxQspi extends BlackBox with HasBlackBoxResource {
  val io = IO(new Bundle() 
  	val i_wb_cyc = Input(Bool())         //Inputs
  	val i_wb_data_stb = Input(Bool())
  	val i_wb_ctrl_stb = Input(Bool())
  	val i_wb_we = Input(Bool())
    val i_wb_addr = Input(UInt(22.W))
    val c = Input(UInt(32.W))

    val o_wb_ack = Output(Bool())       //Outputs
    val o_wb_stall = Output(Bool())
    val o_wb_data= Output(UInt(32.W))

    val o_qspi_sck = Output(Bool())     //Quad SPI connections
    val o_qspi_cs_n = Output(Bool())
    val o_qspi_mod = Output(UInt(2.W))
    val o_qspi_dat = Output(UInt(4.W))
    val i_qspi_dat = Input(UInt(4.W))
  
    val o_interrupt = Output(Bool())
    val o_cmd_accepted = Output(Bool())
    val o_dbg = Output(UInt(32.W))
  })
  setResource("/eqspiflash.v")
}