import java.io.*;
import java.util.*;
public class SA{
	public int[][] sol, safelist, back;
	public int N, nool, noot=0, ppcount=-1, bti=-1, flag=0,scount=-1, removeliz=0;
	public double T;
	public int[] istree, istreecol, islizcol, issafe;
	public static boolean sometree=false;
	public static double maxL;
	public Common c= new Common();
	public int[][] lizplace, pplace;//potential place of lizard  
	public Random random = new Random();
	long time1;
	
	public SA(int No, int noolo, int[][] solo, long time) {
		N=No;
		nool=noolo;
		sol= new int[N][N];
		pplace= new int[N*N][2];
		lizplace= new int[nool][2];
		back= new int[nool][2];
		istree= new int[N];
		istreecol= new int[N];
		time1=time;
		islizcol= new int[N];
		
		//System.out.println(N+" "+nool);
			
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sol[i][j]= solo[i][j];
				islizcol[j]=0;
				//System.out.print(sol[i][j]);
				if(sol[i][j]==2)
				{istree[i]++;//there is a tree in this row
				istreecol[j]++;
				noot++;
				sometree=true;}
				if(sol[i][j]==0)
				{ppcount++;
				pplace[ppcount][0]=i;
				pplace[ppcount][1]=j;
				}
				}
				}
				
		}
		
	
	public void solve() 
	{int N1=N;
		maxL=(N1%2==0)?(Math.pow(N1/2,2)):(Math.pow((N1+1)/2,2));
	try{
    PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
    
			if((sometree==false)&&(nool>N))
			{writer.print("FAIL\n");}
			
			else if((nool>maxL)||(nool>(N+noot)))//add if nool>N+noot
			{writer.print("FAIL\n");}
			
			else if(ppcount<nool-1)
				{writer.print("FAIL\n");}
			
			else{
				placeLizards();
				if(flag==1)
				{
					writer.print("OK\n");
				for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					writer.print(sol[i][j]);
				}
				writer.print("\n");
				}
				}// System.out.println("SUCCESS");
				 else
					  writer.print("FAIL\n");
			}
	writer.close();
	} catch (IOException e){};
	
	}
	
	
	public void placeLizards()
	{
		int[][] temp1= new int[N][N];
		int[][] temp2= new int[N][N];
		for(int i=0;i<N;i++)
		for(int j=0;j<N;j++)
			temp1[i][j]=sol[i][j];
		
		final int[] ints = new Random().ints(0, ppcount+1).distinct().limit(nool).toArray();
		int l=-1;
		for(int i:ints)
		{
			temp1[pplace[i][0]][pplace[i][1]]=1;
			l++;
			lizplace[l][0]=pplace[i][0];
			lizplace[l][1]=pplace[i][1];
		}
		//c.matrixprint(N,temp1);	
		
		int oconf=getconflicts(temp1);
		temp2=temp1;
		
		while(((System.currentTimeMillis())-time1)<180000)
		{long time=System.currentTimeMillis()-time1;
		T=(Math.exp(18000-time))-1;
		//picking a random queen and placing it at a new position
		for(int i=0;i<N;i++)
		for(int j=0;j<N;j++)
			temp2[i][j]=temp1[i][j];
				
		int someliz = random.nextInt(nool);	
		temp2[lizplace[someliz][0]][lizplace[someliz][1]]=0;
		//System.out.println("removed a lizard from "+lizplace[someliz][0]+" "+lizplace[someliz][1]);
		int pflag=0;
		do{
		int newplace = random.nextInt(ppcount+1);//random.nextInt((max - min) + 1) + min

		if(temp1[pplace[newplace][0]][pplace[newplace][1]]==0)
		{
			//System.out.println("Placing new lizard at "+pplace[newplace][0]+" "+pplace[newplace][1]);
			temp2[pplace[newplace][0]][pplace[newplace][1]]=1;
			l++;
			lizplace[someliz][0]=pplace[newplace][0];
			lizplace[someliz][1]=pplace[newplace][1];
		
			pflag=1;
		}
		}while((pflag!=1)&&(((System.currentTimeMillis())-time1)<180000));
		int conf=getconflicts(temp2);
		if(conf==0)
		{
			flag=1;
			//c.matrixprint(N, temp2);
			sol=temp2;
			break;
		}
		else if(conf<oconf)
		{
			//System.out.println("Committing to new state due to less conflicts");
		for(int i=0;i<N;i++)
		for(int j=0;j<N;j++)
			temp1[i][j]=temp2[i][j];
		oconf=conf;
		}
		else if(T>0)
		{
			double prob=Math.exp((oconf-conf)/T);//prob=e^((OldConflictCount-NewConflictCount)/T)
			if((prob)<Math.random())
			{
			//System.out.println("Committing to new state due to probability function");
			for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				temp1[i][j]=temp2[i][j];
			oconf=conf;
			}
		}
	if(flag==1)break;
	}
	if(flag==0)
	{
		flag=2;
		checksa(0, N, nool, 0, 0, bti, back);
	}
	}
	
	public int getconflicts(int[][] matrix)
	{int conf=0;
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(matrix[i][j]==0||matrix[i][j]==2)
					continue;
				else
				{
					if(!c.canPlace(matrix, i, j))
						conf++;
					else
						continue;
				}
			}
		}
		//System.out.println("Conflicts:"+conf);
		return conf;
	}
	
	public void checksa(int lizcounter, int N, int nool, int column, int row, int bti, int back[][]) {
	int backcol=-1, backrow=-1,nextrow=0, nextcol=0, getoutoffor=0, loopflag=0;
	
	do{
	int[] temp= new int[2];
	int loopcount=0;
	for(int i= 0; i<N; i++)
	{
		for(int j=0;j<(N);j++)
		{	loopcount++;
			
			if(sol[j][i]==2)
				continue;
						
			if(removeliz==1)
			{	if(i<nextcol){
				continue;
				}
				if(i==nextcol){
				if(j<nextrow){
				continue;
			}}
			}
						
			removeliz=0;
			nextrow=(j+1)%N;
			nextcol=(nextrow==0)?(i+1)%N:i;
			if ((sol[j][i]==0)&&c.canPlace(sol, j, i))
			{//System.out.println("Placing "+j+i);
				sol[j][i]=1;
				lizcounter++;
				bti++;
				back[bti][0]=j;//row
				back[bti][1]=i;//column
				islizcol[i]++;
				if(lizcounter==nool)
				{
				flag=1;
				break;
				}
				if(islizcol[i]>istreecol[i])
				break;
			}
			if(lizcounter==nool)
			{
			flag=1;
			break;
			}
			if(islizcol[i]>istreecol[i])
			{
					break;
			}
		}
		
		if(flag==1)
		{break;}
		//if(temp[1]==N)
		//break;
		if(getoutoffor==1)
		{break;}
	
		if(islizcol[i]==0)
			if((nool==N)&&(sometree==false))
				break;
		
	}
	if(lizcounter==0)
	if(loopflag==1)
		break;
	if(lizcounter==0)
	if(loopcount==N*N)
		loopflag=1;
	
	//map logic
	if(((bti>=0)&&(flag!=1))||((removeliz==1)&&(bti>=0)))
	{
	backcol=back[bti][1];
	backrow=back[bti][0];
	sol[backrow][backcol]=0;
	lizcounter--;	
	bti--;
	islizcol[backcol]--;
	getoutoffor=0;
	
	nextrow=(backrow+1)%N;
	if (nextrow==0)
	{nextcol=(backcol+1);}
	else
	{nextcol=backcol;}
	removeliz=1;}
	
	} while((flag!=1)&&((System.currentTimeMillis()-time1)<295000));
	
	if(lizcounter==nool)
	{
	flag=1;
	return;
	}
	}
	
}

	

