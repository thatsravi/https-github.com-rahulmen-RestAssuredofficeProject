package getRequest;

public class InnerTest {
	
	InnerTest(InnerStatic a){
		
	}
	
	public static class InnerStatic{
		int a,b;
		
		InnerStatic(int a,int b){
			this.a=a;
			this.b=b;
		}
		
		
		
		public int getA() {
			return a;
		}



		public void setA(int a) {
			this.a = a;
		}



		public int getB() {
			return b;
		}



		public void setB(int b) {
			this.b = b;
		}



		public InnerTest build() {
			return new InnerTest(this);
		}
	}


}

