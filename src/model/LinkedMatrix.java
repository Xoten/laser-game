package model;

public class LinkedMatrix {
	private Node first;
	private int numRows;
	private int numCols;
	
	
	
	public int getNumCols() {
		return numCols;
	}
	public LinkedMatrix(int m, int n) {
		numRows = m;
		numCols = n;
		createMatrix();
	}
	public int getNumRows() {
		return numRows;
	}
	
	private void createMatrix() {
	
		first = new Node(0,0);
		createRow(0,0,first);
	}

	private void createRow(int i, int j, Node currentFirstRow) {
	
		createCol(i,j+1,currentFirstRow,currentFirstRow.getUp());
		if(i+1<numRows) {
			Node downFirstRow = new Node(i+1,j);
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1,j,downFirstRow);
		}
	}

	private void createCol(int i, int j, Node prev, Node rowPrev) {
		if(j<numCols) {
			
			Node current = new Node(i, j);
			current.setPrev(prev);
			prev.setNext(current);
			
			if(rowPrev!=null) {
				rowPrev = rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}
			
			createCol(i,j+1,current,rowPrev);
		}
	}
	
	public String toString() {
		String msg;
		msg = toStringRow(first);
		return msg;
	}

	private String toStringRow(Node firstRow) {
		String msg = "";
		if(firstRow!=null) {
			msg = toStringCol(firstRow) + "\n";
			msg += toStringRow(firstRow.getDown());
		}
		return msg;
	}

	private String toStringCol(Node current) {
		String msg = "";
		if(current!=null) {
			msg = current.toString();
			msg += toStringCol(current.getNext());
		}
		return msg;
	}
	
	public String toString2() {
		String msg = "";
		
		return msg;
	}
	
	public Node searchInRow(Node current, int numRow, int numColumn) {
		Node search = null;
		if(current!=null) {
			if(current.getRow() == numRow && current.getCol() == numColumn) {
				return current;
			}else {
				search = searchInRow(current.getNext(), numRow, numColumn);
			}
		}
		return search;
	}
	
	
	public Node searchPosition(Node current, int numRow, int numColumn) {
		Node search = null;
		if(current!=null) {
			if(current.getRow() == numRow && current.getCol() == numColumn) {
				return current;
			}else {
				search = searchInRow(current.getNext(),numRow,numColumn);
			}
			if(search==null) {
				search = searchPosition(current.getDown(),numRow,numColumn);
			}
		}
		return search; 
	}
	
	
	
	public boolean LocateMirror(int rowFire, int ColFire, String inclination) {
		
	  boolean guessed;
	    String mirrorinc = "";
	    if(inclination.equalsIgnoreCase("L")) {
	    	
	       mirrorinc = "\\";
	    	
	    }else {
	    	
	    	mirrorinc = "/";
	    }
		Node toLocate = searchPosition(first, rowFire-1, ColFire-1);
		
		if(toLocate.getMirror() != null) {
			
			if(toLocate.getMirror().equalsIgnoreCase(mirrorinc)){	
				
			toLocate.setValue(toLocate.getMirror());	
			guessed = true;
			
			}else {
				toLocate.setValue("X");	
				guessed = false;
			}
			
		}else {
			toLocate.setValue("X");
			guessed = false;
		}
		
		return guessed;
		
		
	}
	
	//
	
	public String DetermineInitialDirection(Node ToLocateStart) {
		
		String InitialD = "";
		if(ToLocateStart.getUp() == null) {
			
			InitialD = "down";
			
		}else if(ToLocateStart.getDown() == null) {
			InitialD = "up";
			
		}else if(ToLocateStart.getPrev() == null) {
			InitialD = "right";
			
			
		}else if(ToLocateStart.getNext() == null) {
			
			InitialD = "left";
		}
		
		return InitialD;
	}
	
	public String DetermineInitialDirectionatCorner(String orientation, Node ToLocateStart) {
		
		
	    String Direction = "";
		
      if(ToLocateStart.getUp() == null ) {
    	  
      
         if(orientation.equalsIgnoreCase("H") && ToLocateStart.getNext() == null) {
         
        	 Direction = "left";
        	 
         }else {
         
         Direction = "right";
         
         }if(orientation.equalsIgnoreCase("V")) {
         
        
        	 Direction = "down";
        	 
         }
         
        
      
	}
      
      if(ToLocateStart.getDown() == null) {
    	  
    	  if(orientation.equalsIgnoreCase("H") && ToLocateStart.getNext() == null) {
    	         
         	 Direction = "left";
         	 
          }else {
          
          Direction = "right";
    	  
          } if(orientation.equalsIgnoreCase("V")) {
          
         
         	 Direction = "up";
    	  	  
      }
      
      }
	
          return Direction;
      
	}
	
	
	public Node Shoot(Node ShootEnd, String initialTrayect) {
		
		if(initialTrayect.equalsIgnoreCase("down")) {
			
			if(ShootEnd.getDown() != null) {
				
				if(ShootEnd.getDown().getMirror().equals("\\")) {
					
					
					initialTrayect = "right";
					ShootEnd = ShootEnd.getDown();
					   return Shoot(ShootEnd, initialTrayect);
				}else if(ShootEnd.getDown().getMirror().equals("/")) {
					
					initialTrayect = "left";
					ShootEnd = ShootEnd.getDown();
					   return Shoot(ShootEnd, initialTrayect);
				}else {
				
			
				
				ShootEnd = ShootEnd.getDown();
			    return Shoot(ShootEnd, initialTrayect);
				}
			
			}
			
			return ShootEnd;
			
		}
			
			if(initialTrayect.equalsIgnoreCase("up")) {
				
				if(ShootEnd.getUp() != null) {
					
					if(ShootEnd.getUp().getMirror().equals("\\")) {
						
						
						initialTrayect = "left";
						ShootEnd = ShootEnd.getUp();
						  return  Shoot(ShootEnd, initialTrayect);
					}else if(ShootEnd.getUp().getMirror().equals("/")) {
						
						initialTrayect = "right";
						ShootEnd = ShootEnd.getUp();
						   Shoot(ShootEnd, initialTrayect);
					}else {
				
				   ShootEnd = ShootEnd.getUp();
				   return Shoot(ShootEnd, initialTrayect);
				   
					}
				}
				
				return ShootEnd;
				
			}
				if(initialTrayect.equalsIgnoreCase("Right")) {
					
					if(ShootEnd.getNext() != null) {
						
						if(ShootEnd.getNext().getMirror().equals("\\")) {
							
							
							initialTrayect = "down";
							ShootEnd = ShootEnd.getNext();
							   return Shoot(ShootEnd, initialTrayect);
						}else if(ShootEnd.getNext().getMirror().equals("/")) {
							
							initialTrayect = "up";
							ShootEnd = ShootEnd.getNext();
							   return Shoot(ShootEnd, initialTrayect);
						}else {
				
						ShootEnd = ShootEnd.getNext();
					   return Shoot(ShootEnd, initialTrayect);
					   
						}
					
					}
					
					return ShootEnd;
					
				}
					if(initialTrayect.equalsIgnoreCase("Left")) {
						
						if(ShootEnd.getPrev() != null) {
							
							if(ShootEnd.getPrev().getMirror().equals("\\")) {
								
								
								initialTrayect = "up";
								ShootEnd = ShootEnd.getPrev();
								   return Shoot(ShootEnd, initialTrayect);
							}else if(ShootEnd.getDown().getMirror().equals("/")) {
								
								initialTrayect = "down";
								ShootEnd = ShootEnd.getPrev();
								   return Shoot(ShootEnd, initialTrayect);
								
								
							}else {
						
							
							ShootEnd = ShootEnd.getPrev();
						  return Shoot(ShootEnd, initialTrayect);
						   
							}
						}
					}
					return ShootEnd;
	}
    
	
	
	
	
	

	
	public void generateRandomMirrors(int k, int n, int m) {
		if(k!=0) {
			int numRandomN = (int)(Math.random()*n+1)-1;
			int numRandomM = (int)(Math.random()*m+1)-1;
			Node search = searchPosition(first, numRandomN, numRandomM);
			if(search.getMirror().equals("")) {
				int mirrorRandom = (int)(Math.random()*2+1);
				if(mirrorRandom==1) {
					search.setMirror("/");
				}else if(mirrorRandom==2) {
					search.setMirror("\\");
				}
				generateRandomMirrors(k-1,n,m);
			}else {
				generateRandomMirrors(k, n, m);
			}
		}
	}
	
	
}
