import serial
port = input("Enter the name of COM Port to initialize:") #this has to be the name of the port like COM8 
baud = input("Enter baud rate")
ser = serial.Serial(
        port=port,
        baudrate=baud,
        parity=serial.PARITY_NONE,
        stopbits=serial.STOPBITS_ONE,
        xonxoff=0,
        rtscts=0,
        timeout=100)
print("Listening on COM port (press CTRL+C to cancel) ")
try:
	    while True:
	        ser.write(b'hello') 
	        ser.read(100)
	        print("Data sending starts...")
	        file = open('myfile.txt', 'r') #reads hex file
	        listOfLines = file.readlines()    

		with open("myfile.txt") as file_in:
			    lines = []
			    for line in file_in:
			        lines.append(line)

	        n = 8
	        byte = [lines[i:i+n] for i in range(0, len(lines), n)]  
		print(byte)
		ser.write(byte)
		ser.write(stopbits)
except KeyboardInterrupt:
    print("Keyboard interrupt caught: Shutting down COM port")
ser.close
