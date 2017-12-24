import java.io.*;
import java.util.*;
public class BFS{
	public int[][] sol, safelist;
	public int N, nool, noot=0,flag=0,scount=-1;
	public int[] istree, isliz;
	public static boolean sometree=false;
	public static double maxL;
	public Common c= new Common();
	public Queue<int[][]> q = new LinkedList<>();
	public static Queue<Integer> one = new LinkedList<>();
	public static Queue<Integer> sfp = new LinkedList<>();
	//additional queues
	public Queue<int[][]> q1 = new LinkedList<>();
	public static Queue<Integer> one1 = new LinkedList<>();
	public static Queue<Integer> sfp1 = new LinkedList<>();
	public static long time1,time2;
	
	public BFS(int No, int noolo, int[][] solo) {
		time1 = System.currentTimeMillis();
		N=No;
		nool=noolo;
		sol= new int[N][N];
		
		istree= new int[N];
		isliz= new int[N];
		//System.out.println(N+" "+nool);
			
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sol[i][j]= solo[i][j];
				//System.out.print(sol[i][j]);
				if(sol[i][j]==2)
				{istree[i]++;//there is a tree in this row
				noot++;
				sometree=true;}
				}
				//System.out.println();
				}
				
		}
	
	public void solve() 
	{int N1=N;
		maxL=(N1%2==0)?(Math.pow(N1/2,2)):(Math.pow((N1+1)/2,2));
	try{
    PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
    
			if((sometree==false)&&(nool>N))
			{writer.print("FAIL\n");}
			
			else if((nool>maxL)||(nool>(N+2*noot)))//add if nool>N+noot
			{writer.print("FAIL\n");}
			
			else{
				makesafearray();
				if(nool>scount)
				{
					{writer.print("FAIL\n");}
				}
				else{
				q.add(sol);
				one.add(0);//for nool in that node
				sfp.add(0);
				placeLizards();
				if(flag==1)
				{writer.print("OK\n");
				for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					writer.print(sol[i][j]);
				}
				writer.print("\n");
				}
				 
				}
				 else
					 writer.print("FAIL\n");}
			}
		// time2 = System.currentTimeMillis();
		// long timeTaken = (time2 - time1);  
		// writer.println("Time taken " + timeTaken + " ms");
					
	writer.close();
	} catch (IOException e){};
	}
	
	public void makesafearray()
	{int tempcount=0;
		safelist= new int[N*N+1][3];
		scount++;
		safelist[scount][0]=0;//row
		safelist[scount][1]=0;//col
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(sol[i][j]==0)
				{ tempcount++;	continue;}
				
				else if((sol[i][j]==2)&&(j!=N-1))
				{ tempcount++;
					scount++;
					safelist[scount][0]=i;//row
					safelist[scount][1]=j+1;//col
					safelist[scount-1][2]=tempcount;//count
				tempcount=0;	
				}
			}
			scount++;
					safelist[scount][0]=i+1;//row
					safelist[scount][1]=0;//col
					safelist[scount-1][2]=tempcount;//count
			if(i!=N-1)tempcount=0;
		}
		safelist[scount-1][2]=tempcount;
		// for(int n=0; n<scount; n++)
		// {
			// System.out.println(safelist[n][0]+"||"+safelist[n][1]+"||"+safelist[n][2]);
		// }
	}
	
	public void placeLizards()
	{
	while((!q.isEmpty())&&((System.currentTimeMillis()-time1)<275000)){
		int sflag=0;
		//System.out.println("in the main while loop ");
	int temp[][]= new int[N][N];
		temp=q.remove();
		int liz=one.remove();
		int safepart=sfp.remove();
		//System.out.println("removing from queue:");
		//	c.matrixprint(N,temp);
			//nsrow=nsr.remove();
			//nscol=nsc.remove();
				
		//for(int K=liz;K<scount;K++){
			sflag=0;
			int count=0;
			int i=safelist[safepart][0];
			int j=safelist[safepart][1];
				
			while(count<safelist[safepart][2]){
				//System.out.println("in while loop which must iterate "+safelist[safepart][2]+" and count is "+count);
				count++;
				int temp1[][]= new int[N][N];
				for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					temp1[row][col]=temp[row][col];
				}
				}
				int liz1=liz;
				if(temp1[i][j]==2)
					continue;
				//System.out.println("checking before adding queue");
				if((temp1[i][j]==0)&&(c.canPlace(temp1,i,j)))
				{	
				//	System.out.println("adding to queue:");
					temp1[i][j]=1;
					q.add(temp1);
					liz1++;//no of lizards in this node
					one.add(liz1);
					sfp.add(safepart+1);
					sflag=1;
				//	c.matrixprint(N,temp1);
				//	System.out.println("liz:"+liz1);
					if(liz1==nool)
					{
					flag=1;
					for (int icounter = 0; icounter < N; icounter++) {
					for (int jcounter = 0; jcounter < N; jcounter++) {
					sol[icounter][jcounter]=temp1[icounter][jcounter];
					}
					}
					break;
					}

				}
			j++;	
		//	System.out.println("new col value "+j);	
		}if (flag==1) break;
	if((sflag==0)&&((scount-safepart)>=(N-liz)))
	{
		int temp1[][]= new int[N][N];
				for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					temp1[row][col]=temp[row][col];
				}
				}
			//	System.out.println("in sflag loop:");
			//	c.matrixprint(N,temp1);
		q.add(temp1);
		one.add(liz);
		//safepart++;
		sfp.add(safepart+1);
	}
	if((scount-safepart-1)>=(N-liz))
	{
	int temp1[][]= new int[N][N];
				for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					temp1[row][col]=temp[row][col];
				}
				}
		//		System.out.println("in sflag loop:");
	//			c.matrixprint(N,temp1);
		q1.add(temp1);
		one1.add(liz);
		//safepart++;
		sfp1.add(safepart+1);	
	}
	
	//	}if (flag==1) break;
	}if (flag==1) return;
	else placeLizards1();
	}
	
	
	public void placeLizards1()
	{
	while((!q1.isEmpty())&&((System.currentTimeMillis()-time1)<275000)){
		int sflag=0;
		//System.out.println("in the main while loop of PL1 ");
	int temp[][]= new int[N][N];
		temp=q1.remove();
		int liz=one1.remove();
		int safepart=sfp1.remove();
		//System.out.println("removing from queue:");
			//c.matrixprint(N,temp);
			sflag=0;
			int count=0;
			int i=safelist[safepart][0];
			int j=safelist[safepart][1];
				
			while(count<safelist[safepart][2]){
				//System.out.println("in while loop which must iterate "+safelist[safepart][2]+" and count is "+count);
				count++;
				int temp1[][]= new int[N][N];
				for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					temp1[row][col]=temp[row][col];
				}
				}
				int liz1=liz;
				if(temp1[i][j]==2)
					continue;
				//System.out.println("checking before adding queue");
				if((temp1[i][j]==0)&&(c.canPlace(temp1,i,j)))
				{	
					//System.out.println("adding to queue:");
					temp1[i][j]=1;
					q1.add(temp1);
					liz1++;//no of lizards in this node
					one1.add(liz1);
					//safepart++;
					sfp1.add(safepart+1);
					sflag=1;
					//c.matrixprint(N,temp1);
					//System.out.println("liz:"+liz1);
					if(liz1==nool)
					{
					flag=1;
					for (int icounter = 0; icounter < N; icounter++) {
					for (int jcounter = 0; jcounter < N; jcounter++) {
					sol[icounter][jcounter]=temp1[icounter][jcounter];
					}
					}
					break;
					}

				}
			j++;	
			//System.out.println("new col value "+j);	
		}if (flag==1) break;
	if((sflag==0)&&((scount-safepart)>=(N-liz)))
	{
		int temp1[][]= new int[N][N];
				for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					temp1[row][col]=temp[row][col];
				}
				}
				//System.out.println("in sflag loop:");
				//c.matrixprint(N,temp1);
		q1.add(temp1);
		one1.add(liz);
		//safepart++;
		sfp1.add(safepart+1);
	}
	}//if (flag==1) System.out.println("SUCCESS");
	}

}