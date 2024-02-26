package utils;

/**
 * 用于判断是否删除
 */
public enum DelFlagE {
        YES("Yes",0),NO("NO",1);

        public String name;
        public int code;
        DelFlagE(String name,int code){
            this.name=name;
            this.code=code;
        }
}
