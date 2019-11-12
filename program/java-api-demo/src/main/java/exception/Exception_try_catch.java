package exception;

/**
 * java异常捕获机制
 * try-catch
 * try{
 * ....需要处理异常的代码片段
 * }catch(XXXXException e){
 * ....当try中代码出错后在这里捕获并处理
 * }
 *
 * @author Bean
 */
public class Exception_try_catch {
    public static void main(String[] args) {
        System.out.println("程序开始了.");
        try {
            String str = "3.14";
            System.out.println(str.length()); //str=null throw NullPointerException
            System.out.println(str.charAt(0)); //str.length()=0 throw StringIndexOutOfBoundsException
            System.out.println(Integer.parseInt(str)); //str里内容非数字时 throw NumberFormatException

            //try中出错代码以下的内容都不执行
            System.out.println("!!!!");
        } catch (NullPointerException e) {
            System.out.println("出现了空指针!");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("字符串下标越界了!");
            /*
             * catch可以指定多个，当针对不同的异常
             * 有不同的处理手段时，可以分别捕获它们
             * 但是应当养成一个好习惯，在最后一个catch
             * 中捕获Exception，这样不会因为一个未捕获
             * 的异常导致程序中断.
             */
        } catch (Exception e) {
//			System.out.println("反正就是出了错!");
            e.printStackTrace();
        }

        System.out.println("程序结束了.");
    }
}
