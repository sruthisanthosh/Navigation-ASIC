import chisel3._
import chisel3.util._


class BlackBoxWB extends BlackBox with HasBlackBoxResource {
  val io = IO(new Bundle() 

    val input_axis_tdata = Input(UInt(8.W))
    val input_axis_tkeep = Input(UInt(1.W))
    val input_axis_tvalid = Input(Bool())  
    val input_axis_tready = Output(Bool())  
    val input_axis_tlast = Input(Bool())  
    val input_axis_tuser = Input(Bool())  

    val output_axis_tdata = Output(UInt(8.W))
    val output_axis_tkeep = Output(UInt(1.W))
    val output_axis_tvalid = Output(Bool()) 
    val output_axis_tready = Input(Bool()) 
    val output_axis_tlast = Output(Bool()) 
    val output_axis_tuser = Output(Bool()) 

    val wb_adr_o =  Output(UInt(32.W))  // ADR_O() address
    val wb_dat_i = Input(UInt(32.W))// DAT_I() data in
    val wb_dat_o = Output(UInt(32.W))
    val wb_we_o = Output(Bool())  
    val wb_sel_o = Output(UInt(1.W))
    val wb_stb_o = Output(Bool())  
    val wb_ack_i = Input(Bool())  // ACK_I acknowledge input
    val wb_err_i = Input(Bool())    // ERR_I error input
    val wb_cyc_o = Output(Bool())   // CYC_O cycle output
    val busy = Output(Bool())  


  })
  setResource("/wb_master.v")
}
  
