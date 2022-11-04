package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TEST1 {
	
	public static List<GraphDataVO> searchList(List<GraphDataVO> listAll, String clothType, String sellPeriod){
		
		List<GraphDataVO> resultList = new ArrayList<>();  
		for(int i=0; i<listAll.size(); i++) {
			GraphDataVO graphdataVO = listAll.get(i);
			
			if(graphdataVO.getCt().equals(clothType) && graphdataVO.getPeriod().equals(sellPeriod)) {
				resultList.add(graphdataVO);
			}
		}
		
		return resultList;
	}
	
	public static void main(String[] args) {
		
		List<GraphDataVO> listAll = new ArrayList<>();
		
		listAll.add(new GraphDataVO("t-001", 23213, "2209", "ct-1"));
		listAll.add(new GraphDataVO("p-001", 13213, "2209", "ct-2"));
		listAll.add(new GraphDataVO("o-001", 23113, "2208", "ct-3"));
		listAll.add(new GraphDataVO("n-001", 31213, "2209", "ct-4"));
		listAll.add(new GraphDataVO("j-001", 13213, "2207", "ct-5"));
		listAll.add(new GraphDataVO("j-002", 13113, "2206", "ct-5"));
		listAll.add(new GraphDataVO("j-003", 23213, "2207", "ct-5"));
		listAll.add(new GraphDataVO("n-001", 33213, "2206", "ct-4"));
		listAll.add(new GraphDataVO("n-002", 11213, "2208", "ct-4"));
		listAll.add(new GraphDataVO("o-002", 12213, "2209", "ct-3"));
		listAll.add(new GraphDataVO("o-003", 12113, "2209", "ct-3"));
		listAll.add(new GraphDataVO("p-002", 13203, "2208", "ct-2"));
		listAll.add(new GraphDataVO("p-001", 10213, "2207", "ct-2"));
		listAll.add(new GraphDataVO("p-003", 13013, "2206", "ct-2"));
		listAll.add(new GraphDataVO("p-004", 43213, "2209", "ct-2"));
		listAll.add(new GraphDataVO("p-003", 10213, "2209", "ct-2"));
		listAll.add(new GraphDataVO("t-002", 13313, "2208", "ct-1"));
		listAll.add(new GraphDataVO("j-004", 23213, "2208", "ct-5"));
		listAll.add(new GraphDataVO("j-003", 13213, "2207", "ct-5"));
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("입력: ");
		String clothType = sc.nextLine();
		
		System.out.print("입력: ");
		String sellPeriod = sc.nextLine();
		
		searchList(listAll, clothType, sellPeriod).forEach(x -> System.out.println(x));
		
		sc.close();
		
		
	}
}
