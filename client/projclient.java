import java.io.*;
import java.net.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.zip.*;
class projclient
{
JFrame f,f1,f2,f3,home,f4;
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
JTextField t,t1,t2,t3,t4,t5,t6;
JButton b,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
JPasswordField p1;
int i;
long total,start;
JList<String> file;
JScrollPane sc;

public void cli()
{
	
f4=new JFrame("LOGIN AREA");
l9=new JLabel("Username :");  l9.setBounds(40,100,100,30);
l10=new JLabel("Password :");  l10.setBounds(40,140,100,30);
t5=new JTextField();   t5.setBounds(210,100,200,30);
p1=new JPasswordField();   p1.setBounds(210,140,200,30);
b10=new JButton("Login");  b10.setBounds(150,200,100,30);
	
b10.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{
          String user=t5.getText(); 
          String pass=p1.getText();
          
if(user.equals("122003216") && pass.equals("07072000"))	
{
f1=new JFrame("HOME");
l2=new JLabel("TRANSPORT LAYER PROTOCOL");  l2.setBounds(100,80,200,30);
b1=new JButton("MULTICAST");  b1.setBounds(100,200,200,30);	
b2=new JButton("FILE TRANSFER");  b2.setBounds(100,250,200,30);	
b7=new JButton("DATA RATE");  b7.setBounds(100,300,200,30);	

b1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{ 
f2=new JFrame("UDP CLIENT");
l3=new JLabel("Waiting for multicast message...");  l3.setBounds(80,100,200,30);
b4=new JButton("Receive");  b4.setBounds(130,400,100,30);
b5=new JButton("Back");  b5.setBounds(30,20,100,30);
	String[] str=new String[7];
    str[0]="welcome";
	str[1]="to Multicast";
file = new JList<>(str);  
sc= new JScrollPane(file);	sc.setBounds(80,150,200,200);

          b4.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		byte[] buffer=new byte[1024];
      MulticastSocket socket=new MulticastSocket(4321);
      InetAddress group=InetAddress.getByName("230.0.0.0");
      socket.joinGroup(group);
	  ArrayList<String> ls = new ArrayList<String>();

      while(true){
         System.out.println("Waiting for multicast message...");
         DatagramPacket packet=new DatagramPacket(buffer,buffer.length);
         socket.receive(packet);
         String msg=new String(packet.getData(),packet.getOffset(),packet.getLength());
		  System.out.println("[Multicast UDP message received]    "+msg);
		 ls.add(msg);
		 byte buff[] = msg.getBytes();
  ByteArrayInputStream bais = new ByteArrayInputStream(buff);
  CheckedInputStream cis = new CheckedInputStream(bais, new Adler32());
  byte readBuffer[] = new byte[1024];
  while (cis.read(readBuffer) >= 0){
  long value = cis.getChecksum().getValue();
  System.out.println("The value of checksum is " + value);
  
  }
        
  
         if("exit".equals(msg)) {
            System.out.println("No more message. Exiting : "+msg);
            break;
			
		 }
		 
         
      }
	  l3.setText("No more message. Exiting...");
	  String[] s1=  ls.toArray(new String[]{});
	  file.setListData(s1);
	  
      socket.leaveGroup(group);
      socket.close();
		}
		catch(Exception E)
						  {
							  System.out.println("error");
						  }
		

		  }});		
                        

b5.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{
		f2.setVisible(false);
		f1.setVisible(true);
		
		
}});


f2.add(l3);
f2.add(b4);
f2.add(b5);
f2.add(sc);

f1.setVisible(false);
f2.setSize(500,600);
f2.setLayout(null);
f2.setVisible(true);
f2.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
f2.getContentPane().setBackground(new java.awt.Color(204, 166, 166));
	
	
}});





b2.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{ 
	 client c = new client();
     c.cli();
	 
	 
	 
}});

b7.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{ 
	
	try{
	
	f3=new JFrame("TRANSFER RATE");
l7=new JLabel("Enter the port number:");  l7.setBounds(50,20,200,30);
t4=new JTextField();   t4.setBounds(50,50,200,30);
l6=new JLabel("Calculating Speed.....");  l6.setBounds(100,100,200,30);
l5=new JLabel("Status");  l5.setBounds(100,150,200,30);
b8=new JButton("Run");  b8.setBounds(100,220,100,30);	
b9=new JButton("Back");  b9.setBounds(100,300,100,30);
	
 
	 
b8.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{ 
	try{
		Socket socket = new Socket("127.0.0.1",6666);
        InputStream input = socket.getInputStream();
        total = 0;
        start = System.currentTimeMillis();

        byte[] bytes = new byte[32*1024]; // 32K
	i=1;
		
	 for(int i=1;;i++)
     {		 
		 
	int read = input.read(bytes);
            if (read < 0)break;
            total += read;
            if (i % 500000 == 0) {
                long cost = System.currentTimeMillis() - start;
				
				String sum="Speed: " + String.valueOf(total/cost/1000) + " MB/s";
                System.out.printf("Read %,d bytes, speed: %,d MB/s%n", total, total/cost/1000);
				l5.setText(sum);
				break;
			}
			
	 }
	 
	
	}
	catch(Exception E)
						  {
							  System.out.println("error");
						  }
	

}});


b9.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{ 
      f1.setVisible(true);
	  f3.setVisible(false);

}});


}
catch(Exception E)
						  {
							  System.out.println("error");
						  }



f3.add(l5);
f3.add(b8);
f3.add(b9);
f3.add(l6);
f3.add(l7);
f3.add(t4);
f1.setVisible(false);
f3.setSize(500,500);
f3.setLayout(null);
f3.setVisible(true);
f3.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
f3.getContentPane().setBackground(new java.awt.Color(204, 166, 166));
	
	
	
}});

f1.add(l2);
f1.add(b1);
f1.add(b2);
f1.add(b7);

f1.setSize(500,600);
f1.setLayout(null);
f1.setVisible(true);
f4.setVisible(false);
f1.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
f1.getContentPane().setBackground(new java.awt.Color(204, 166, 166));

}
else
{
	JOptionPane.showMessageDialog(f4,"Invalid Username or Password.","Alert",JOptionPane.WARNING_MESSAGE);
	
}



}});



f4.add(l9);
f4.add(l10);
f4.add(t5);
f4.add(p1);
f4.add(b10);
f4.setSize(600,500);
f4.setLayout(null);
f4.setVisible(true);
f4.setDefaultCloseOperation(f4.EXIT_ON_CLOSE);
f4.getContentPane().setBackground(new java.awt.Color(204, 166, 166));




}
	public static void main(String args[]) {

                 projclient c = new projclient();
                  c.cli();
                       
}
}

class client 
{
JFrame f,f1,f2,f3,f4;
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
JTextField t,t1,t2,t3,t4;
JButton b,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16;
JList<String> file;
JScrollPane sc;
 Socket s;	
	
	
public void cli()
{
f=new JFrame("TCP CLIENT");
l1=new JLabel("Enter the port number:");  l1.setBounds(50,20,200,30);
t=new JTextField();   t.setBounds(50,50,200,30);
b=new JButton("Connect");  b.setBounds(350,50,100,30);
l3=new JLabel("Status");  l3.setBounds(70,100,200,30);
l2=new JLabel("Perform any of the operation:");  l2.setBounds(170,150,200,30);
b1=new JButton("List");    b1.setBounds(200,200,100,30);
b2=new JButton("Options");   b2.setBounds(200,250,100,30);
b3=new JButton("Rename");    b3.setBounds(200,300,100,30);
b4=new JButton("Set/check");    b4.setBounds(200,350,100,30);
b16=new JButton("Exit");    b16.setBounds(200,450,100,30);

b16.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)   
	{
		
	f.setVisible(false);	
		
}});
		


b.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)   
	{
		
		
		try{
     s=new Socket("localhost",Integer.parseInt(t.getText()));
	 l3.setText("Successfully Connected");
	 }
	 catch (Exception E)
	 {
		 l3.setText("Invalid Port no.");
	 }
				
		
}});

b1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{  
	
	
	String[] str=new String[7];
    str[0]="It's Show Time...";
	str[1]="Let's Begin...";
	

	f1=new JFrame("LIST");
	l5=new JLabel("Enter the file or dir name:");     l5.setBounds(50,50,200,30);
	t4=new JTextField();     t4.setBounds(50,100,200,30);
	b11=new JButton("Ok");   b11.setBounds(300,100,100,30);
    file = new JList<>(str);  
    sc= new JScrollPane(file);	sc.setBounds(450,40,400,400);
    b5=new JButton("Back");    b5.setBounds(100,300,100,30);
	b5.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{      
       f1.setVisible(false);
		f.setVisible(true);
	}
		 });	
		 
		 
		b11.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{       
	
	try
{
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
DataInputStream din = new DataInputStream(s.getInputStream());
dout.writeUTF("lb");
String name = t4.getText();
dout.writeUTF(name);
String dis = din.readUTF();
int len =dis.length();
String[] arr=new String[len];
int i=0;
String mak="";
int k=0;
while(i<dis.length())
{
	char s = dis.charAt(i);
	int c=Character.compare(s,',');
	if(c==0)
	{
	arr[k]=mak;
	k++;
	mak="";
	i++;
	}
    else{
	mak=mak+dis.charAt(i);
     i++;
	}
}


file.setListData(arr);
}
catch (Exception E)
{
    l4.setText("ERROR");
}
                
	
		 
		}});	 
		f1.add(b5);
		f1.add(l5);
		f1.add(t4);
		f1.add(b11);
		f1.add(sc);
		f1.setSize(1000,600);
		f1.setLayout(null);
		f1.setVisible(true);
		f.setVisible(false);
		f1.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);
		f1.getContentPane().setBackground(new java.awt.Color(204, 166, 166));
	
	
	
	
	
	
	
	
}});

b2.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e) 
	{
    f2=new JFrame("OPTIONS");
	l5=new JLabel("Enter the file or dir name:");     l5.setBounds(50,100,200,30);
    t1=new JTextField();     t1.setBounds(50,150,200,30);
	l6=new JLabel("Status");     l6.setBounds(300,150,200,30);
    b5=new JButton("Back");    b5.setBounds(100,400,100,30);
	b6=new JButton("Create");    b6.setBounds(50,250,100,30);
	b7=new JButton("Delete");    b7.setBounds(170,250,100,30);
	b8=new JButton("Upload");    b8.setBounds(290,250,100,30);
	b9=new JButton("Download");    b9.setBounds(410,250,100,30);
	b5.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{      
       f2.setVisible(false);
		f.setVisible(true);
	}
		 });	
		 
		 
	b6.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{  
		   try{
DataOutputStream dout = new DataOutputStream(s.getOutputStream());
String str = t1.getText();
if(str.equals("") || str.contains(" "))
{
	l6.setText("Enter valid Name");
}
else
{
dout.writeUTF("cb");
String size = Integer.toString(str.length());
dout.writeUTF(size);
for ( int i = 0 ; i < str.length() ; i++)
{
	int  n = str.charAt(i);
	n = n + 9 ;
	String nm = Integer.toString(n);
	dout.writeUTF(nm);
}

l6.setText("Folder Created");
}
  }
   catch(Exception E)
  {
	  l6.setText("ERROR");
   }	   
		 
	}});	 
	
	
	
	
	b7.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{  
		 try{
		 DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 DataInputStream din = new DataInputStream(s.getInputStream());
         dout.writeUTF("dlb");
		 String ul= t1.getText();
         dout.writeUTF(ul);
		 String ch23 = din.readUTF();
		 if (ch23.equals("File Found"))
		 {
			l6.setText("File Deleted");
		 }
		 else if(ch23.equals("ERROR"))
		 {
			 l6.setText("ERROR");
		 }
		 else
		 {
		 
		  l6.setText("File not Found");
		 }
	 }
	 catch(Exception E)
	 {
		 l6.setText("ERROR");
	 }
		 
	}});	 
	
	
	
	
	
    b8.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{  
		 try{
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
String ul= t1.getText();
File fn = new File(ul);
if(ul.contains("\\"))
{
ul = ul.substring(ul.lastIndexOf("\\"));
}
if(fn.exists())
{
dout.writeUTF("ub");
dout.writeUTF(ul);
byte arr[] = new byte[(int) fn.length()];
FileInputStream fi = new FileInputStream(fn);
fi.read(arr,0,arr.length);
dout.write(arr,0,arr.length);
dout.write(-1);
dout.flush();
fi.close();
l6.setText("Uploaded Successfully");
	 }
else
{
	l6.setText("File not Found");
}	
		 }
	 catch (Exception E)
	 {
		 l6.setText("ERROR");
	 }
		 
	}});	 
	
	
	
	b9.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{  
		 try{
	  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
DataInputStream in = new DataInputStream(s.getInputStream());
dout.writeUTF("db");
String ss=t1.getText();
dout.writeUTF(ss);
if(ss.contains("\\"))
{
ss = ss.substring(ss.lastIndexOf("\\"));
}
String ck=in.readUTF();
if(ck.equals("File Found"))
{
InputStream din = s.getInputStream();
FileOutputStream fo = new FileOutputStream(ss);
byte i;
while(((i=(byte)din.read()) !=-1))
{
	fo.write(i);
}
fo.close();
l6.setText("Downloaded Successfully");
  }
else
{
	l6.setText(ck);
}
		 }	 
  catch(Exception E)
  {
	  l6.setText("ERROR");
  }
		 
	}});	 
		 
		 
		 
		f2.add(l5);
		f2.add(l6);
		f2.add(t1);
		f2.add(b5);
		f2.add(b6);
		f2.add(b7);
		f2.add(b8);
		f2.add(b9);
		f2.setSize(700,550);
		f2.setLayout(null);
		f2.setVisible(true);
		f.setVisible(false);
		f2.setDefaultCloseOperation(f2.EXIT_ON_CLOSE);
		f2.getContentPane().setBackground(new java.awt.Color(204, 166, 166));
		
		
		
		
		
}});

b3.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)   
	{
	f3=new JFrame("RENAME");	
	l5=new JLabel("Enter the file or dir name:");     l5.setBounds(50,100,200,30);
	t2=new JTextField();     t2.setBounds(50,150,200,30);
	l7=new JLabel("Enter the new name:");     l7.setBounds(300,100,200,30);	
	t3=new JTextField();     t3.setBounds(300,150,200,30);	
	b10=new JButton("Ok");    b10.setBounds(50,250,100,30);
	l8=new JLabel("Status");   l8.setBounds(300,250,200,30);
	b5=new JButton("Back");    b5.setBounds(100,400,100,30);
	b5.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{      
       f3.setVisible(false);
		f.setVisible(true);
	}
		 });	
		 
		 
		 b10.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{     
	
	try{
		
		 DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 DataInputStream din = new DataInputStream(s.getInputStream());
         dout.writeUTF("rnm");
		String r1=t2.getText();
		String r2=t3.getText();
		dout.writeUTF(r1);
		dout.writeUTF(r2);
		String chk = din.readUTF();
		  if (chk.equals("File Found"))
		 {
			 l8.setText("File renamed");
		 }
		 else if(chk.equals("ERROR"))
		 {
			 l8.setText("ERROR");
		 }
		 else
		 {
		 
		 l8.setText("File not Found");
		 }
	 }
		 catch(Exception E)
	 {
		 l8.setText("ERROR");
	 }
		 
		 }});
		f3.add(l8);
		f3.add(l5);
		f3.add(l7);
		f3.add(t2);
		f3.add(t3);
		f3.add(b5);
		f3.add(b10);
		f3.setSize(700,550);
		f3.setLayout(null);
		f3.setVisible(true);
		f.setVisible(false);
		f3.setDefaultCloseOperation(f3.EXIT_ON_CLOSE);
		f3.getContentPane().setBackground(new java.awt.Color(204, 166, 166));
		 
		
}});

b4.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)   
	{
	f4=new JFrame("SET/CHECK");	
	l5=new JLabel("Enter the file or dir name:");     l5.setBounds(50,100,200,30);
	t4=new JTextField();     t4.setBounds(50,150,200,30);
	l9=new JLabel("Status");     l9.setBounds(300,150,300,30);
	b12=new JButton("Modify info");    b12.setBounds(50,250,100,30);
	b13=new JButton("Check exec");    b13.setBounds(170,250,150,30);
    b14=new JButton("Set read");   b14.setBounds(340,250,100,30);
	b5=new JButton("Back");    b5.setBounds(100,400,100,30);
	b5.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{      
      f4.setVisible(false);
	  f.setVisible(true);
	}
		});	
		 
  b12.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{ 
	try{
		
		 DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 DataInputStream din = new DataInputStream(s.getInputStream());
         dout.writeUTF("modi");
		String r1=t4.getText();
	    dout.writeUTF(r1);
		String m=din.readUTF();
		if(m.equals("File not Found"))
		{
			l9.setText("File not found");
		}
		else if(m.equals("ERROR"))
		{
			l9.setText("ERROR");
		}
		else
		{
		l9.setText(m);
		}
		
	}
	catch(Exception E)
	 {
		 l9.setText("ERROR");
	 }
	
	
		 }});
		 
		 		 b13.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{ 
	
	try{
		
		 DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 DataInputStream din = new DataInputStream(s.getInputStream());
         dout.writeUTF("exec");
		String r1=t4.getText();
	    dout.writeUTF(r1);
		String ck=din.readUTF();
         if(ck.equals("File not Found"))
		 {
			 l9.setText("File not Found");
		 }
		 else if(ck.equals("ERROR"))
		 {
			 l9.setText("ERROR");
		 }
		 else
		 {
			 l9.setText(ck);
		 }
		
		
	}
	
	catch(Exception E)
	 {
		 l9.setText("ERROR");
	 }
	
	
	
	
	
	
		 }});
		 
	 b14.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e)
	{ 
	try{
		
		 DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 DataInputStream din = new DataInputStream(s.getInputStream());
         dout.writeUTF("rc");
		String r1=t4.getText();
	    dout.writeUTF(r1);
		String k=din.readUTF();
         if(k.equals("File not Found"))
		 {
			 l9.setText("File not Found");
		 }
		 else if(k.equals("ERROR"))
		 {
			 l9.setText("ERROR");
		 }
		 else
		 {
			 l9.setText(k);
		 }
		
		
	}
	
	catch(Exception E)
	 {
		 l9.setText("ERROR");
	 }
	 }});
		 



	
        f4.add(l5);
		f4.add(t4);
		f4.add(l9);
		f4.add(b12);
		f4.add(b13);
		f4.add(b14);
		f4.add(b5);
		f4.setSize(700,550);
		f4.setLayout(null);
		f4.setVisible(true);
		f.setVisible(false);
		f4.setDefaultCloseOperation(f4.EXIT_ON_CLOSE);
		f4.getContentPane().setBackground(new java.awt.Color(204, 166, 166));	
		
		
}});


f.add(l3);
f.add(l2);
f.add(l1);
f.add(t);;
f.add(b);
f.add(b1);
f.add(b2);
f.add(b3);
f.add(b4);
f.add(b16);
f.setSize(500,600);
f.setLayout(null);
f.setVisible(true);
f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
f.getContentPane().setBackground(new java.awt.Color(204, 166, 166));
}


}