 import java.io.*;
 import java.util.*;
 import java.net.*;
 public class homework{
	public int flag=0,  tempflag=0;
	public int backcol=-1, backrow=-1;
	public static long time1, time2, time;
	public static void main(String args[]) throws IOException{	
	URL path = homework.class.getResource("input.txt");
	File f = new File(path.getFile());
	BufferedReader inp = new BufferedReader (new FileReader(f));
    //int T= Integer.parseInt(inp.readLine());
	//sc= new Scanner(new FileReader(f));
	String methodName = inp.readLine();
	//System.out.println(methodName);
	int N= Integer.parseInt(inp.readLine());
	//System.out.println(N);
	int nool=Integer.parseInt(inp.readLine());
	//System.out.println(nool);
	//sample t= new sample(N);
	int i=0,j=0,count=0;
	int[][] sol = new int[N][N];
	for(i=0;i<N;i++)
	{	String line= inp.readLine();
		char[] ch=line.toCharArray();
		for(j=0;j<N;j++)
		{
			String s = ch[j] + "";
			sol[i][j] = Integer.parseInt(s);
		}
	}
	// for(i=0;i<N;i++)
	// {	for(j=0;j<N;j++)
		// {
			// System.out.print(sol[i][j]);
		// }
		// System.out.println();
	// }
if(methodName.equals("BFS"))
{
	BFS b=new BFS(N,nool,sol);
	time1 = System.currentTimeMillis();
	b.solve();
	// time2 = System.currentTimeMillis();
	// time=time2-time1;
	// System.out.println("time"+time);
}
if(methodName.equals("DFS"))
{
	//time1 = System.currentTimeMillis();
	DFS d=new DFS(N,nool,sol);
	d.solve(N,nool);
	// time2 = System.currentTimeMillis();
	// time=time2-time1;
	// System.out.println("time"+time);
}
if(methodName.equals("SA"))
{
	time1 = System.currentTimeMillis();
	SA sa=new SA(N,nool,sol, time1);
	
	sa.solve();
	// time2 = System.currentTimeMillis();
	// time=time2-time1;
	// System.out.println("time"+time);
}
	}
	}