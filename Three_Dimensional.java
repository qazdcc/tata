package com.dcc.three_dim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Three_Dimensional {

	private static int count = 0;//检查次数
	
	public static boolean check_threeDimLoading_LIFO(List<List<Integer>> potentialPlacements,
			List<List<Integer>> freeBoxes,
			List<List<Integer>> currentSolution,int maxApCall){
		
		//如果盒子全部装载
		if(freeBoxes == null)
			return true;
		//超过最大检查次数
		if(count > maxApCall)
			return false;
		count++;
		List<Integer> free = freeBoxes.get(0);
		int[] freebox = new int[3];
		for(int i=0; i<3; i++){
			freebox[i] = free.get(i);
		}
		for(int i=0; i<potentialPlacements.size(); i++){
			List<Integer> potential = potentialPlacements.get(i);
			int[] potentialPlacement = new int[6];
			for(int j=0; j<6; j++){
				potentialPlacement[j] = potential.get(j);
			}
			if(freebox[0]<potentialPlacement[3]&freebox[1]<potentialPlacement[4]&freebox[2]<potentialPlacement[5]){
				//把箱子放到current中，并从freeBoxes中去除
				currentSolution.add(free);
				freeBoxes.remove(0);
				//去掉之前的潜在位置，更新3个新的位置
				potentialPlacements.remove(i);
				changeAll_Info(potentialPlacements, freeBoxes, currentSolution, freebox, potentialPlacement);
				return check_threeDimLoading_LIFO(potentialPlacements, freeBoxes, currentSolution, maxApCall);
			}
			return false;
		}
		
		return false;
		
	}
	/**
	 * 
	 * @param potentialPlacements
	 * @param freeBoxes
	 * @param currentSolution
	 * @param a
	 * @param b
	 */
	public static void changeAll_Info(List<List<Integer>> potentialPlacements,
			List<List<Integer>> freeBoxes,
			List<List<Integer>> currentSolution,int[] a, int[] b){
		List<Integer> a1  = new ArrayList<Integer>();
		List<Integer> a2  = new ArrayList<Integer>();
		List<Integer> a3  = new ArrayList<Integer>();
		//创造第一个新potentialPlacement
		a1.add(a[0]+b[0]);
		a1.add(b[1]);
		a1.add(b[2]);
		a1.add(b[3]-a[0]);
		a1.add(b[4]);
		a1.add(b[5]);
		//创造第二个新potentialPlacement
		a2.add(b[0]);
		a2.add(a[1]+b[1]);
		a2.add(b[2]);
		a2.add(b[3]-a[0]);
		a2.add(b[4]-a[1]);
		a2.add(b[5]);
		//创造第三个新potentialPlacement
		a3.add(b[0]);
		a3.add(b[1]);
		a3.add(a[2]+b[2]);
		a3.add(b[3]-a[0]);
		a3.add(b[4]-a[1]);
		a3.add(b[5]-a[2]);
		//加入potentialP中
		potentialPlacements.add(a1);
		potentialPlacements.add(a2);
		potentialPlacements.add(a3);
		
	}
	
	
	/**
	 * 根据条件，对list进行排序
	 * @param list
	 */
	public static void sortList(List<List<Integer>> list){
		Collections.sort(list, new Comparator<List<Integer>>(){
			@Override
			public int compare(List<Integer> arr1, List<Integer> arr2) {	
				if(arr1.get(0) < arr2.get(0)){
					return -1;
				} else if(arr1.get(0).equals(arr2.get(0))){
					if (arr1.get(1) < arr2.get(1)){
						return -1;
					} else if(arr1.get(1).equals(arr2.get(1))){
						if(arr1.get(1)< arr2.get(1)){
							return -1;
						} else if(arr1.get(1).equals(arr2.get(1))){
							return 0;
						}else{
							return 1;
						}
					} else {
						return 1;
					}
				} else{
					return 1;
				}
			}});
	}
	
	public static void main(String[] args) {
		List<Integer> a1  = new ArrayList<Integer>();
		List<Integer> a2  = new ArrayList<Integer>();
		List<Integer> a3  = new ArrayList<Integer>();
		List<Integer> a4  = new ArrayList<Integer>();
		List<Integer> a5  = new ArrayList<Integer>();
		//创造第一个新potentialPlacement
		a1.add(0);
		a1.add(0);
		a1.add(0);
		a1.add(4);
		a1.add(4);
		a1.add(4);
		//创造第二个新potentialPlacement
		a2.add(1);
		a2.add(2);
		a2.add(1);
		a2.add(4);
		a2.add(4);
		a2.add(4);
		//创造第三个新potentialPlacement
		a3.add(1);
		a3.add(3);
		a3.add(1);
		a3.add(2);
		a3.add(1);
		a3.add(2);
		
		a4.add(1);
		a4.add(2);
		a4.add(2);
		a4.add(2);
		a4.add(1);
		a4.add(2);
		
		a5.add(1);
		a5.add(2);
		a5.add(2);
		a5.add(2);
		a5.add(1);
		a5.add(2);
		List<List<Integer>> potentialPlacements = new ArrayList<List<Integer>>();;
		//加入potentialP中
		potentialPlacements.add(a1);
		potentialPlacements.add(a2);
		potentialPlacements.add(a3);
		potentialPlacements.add(a4);
		potentialPlacements.add(a5);
		
		sortList(potentialPlacements);
		System.out.println(potentialPlacements);
	}
}
