JDiskCollection is an implementation of collections that work on disk rather than memory. It is in beginning and only have implementation of List.

How to use?

    List<String> diskList = null;
		try {
			diskList = new ArrayDiskList<String>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 100000; i++) {
			diskList.add("teste"+i);
			System.out.println("inserted: teste"+i);
		}
		
		for(int i=0; i < diskList.size(); i++) {
			System.out.println("out: "+ diskList.get(i));
		}
		
		
	
