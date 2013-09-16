import java.lang.reflect.*;

public class MethodClass {
		private Class clazz;
		private Object targetObj;
		private Method method;
		private Object[] paramObjs;
		MethodClass(Object obj) {
			this.targetObj = obj;
			this.clazz = obj.getClass();
		}

		public void bindMethod(String methodName, Object ...params) {
			this.paramObjs = params;
			Class paramsClassTypes[] = new Class[params.length];
			for (int i=0; i<params.length; i++) {
				paramsClassTypes[i] = params[i].getClass();
			}
			try {
				this.method = this.clazz.getMethod(methodName, paramsClassTypes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void doMethod() {
			try {
				this.method.invoke(this.targetObj, this.paramObjs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
