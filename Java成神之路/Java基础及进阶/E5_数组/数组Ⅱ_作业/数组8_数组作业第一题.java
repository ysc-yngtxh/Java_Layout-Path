package E5_数组.数组Ⅱ_作业;

/*
 第一题：
    编写程序，使用一维数组，模拟栈数据结构。
    要求：
         1、这个栈可以存储Java中的任何引用类型的数据
         2、在栈中提供push方法模拟压栈。（栈满了，要有提示信息）
         3、在栈中提供pop方法模拟弹栈。 （栈空了，也有提示信息）
         4、编写测试程序，new栈对象，调用push,pop方法来模拟压栈弹栈的动作。
         5、假设栈的默认初始化容量是10  （请注意无参构造方法的编写方式）
 */
public class 数组8_数组作业第一题 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        try {
            stack.push(new Object());      // 压栈java.lang.Object@1540e19d成功，栈帧指向0
            stack.push(new Object());      // 压栈java.lang.Object@677327b6成功，栈帧指向1
            stack.push(new Object());      // 压栈java.lang.Object@14ae5a56成功，栈帧指向2
            stack.push(new Object());      // 压栈java.lang.Object@7f31245a成功，栈帧指向3
            stack.push(new Object());      // 压栈java.lang.Object@6d6f6e28成功，栈帧指向4
            stack.push(new Object());      // 压栈java.lang.Object@135fbaa4成功，栈帧指向5
            stack.push(new Object());      // 压栈java.lang.Object@45ee12a7成功，栈帧指向6
            stack.push(new Object());      // 压栈java.lang.Object@330bedb4成功，栈帧指向7
            stack.push(new Object());      // 压栈java.lang.Object@2503dbd3成功，栈帧指向8
            stack.push(new Object());      // 压栈java.lang.Object@4b67cf4d成功，栈帧指向9
            stack.push(new Object());      // 压栈失败，栈已满！
        } catch (MyStackOperationException e){
            System.out.println(e.getMessage());
        }

        // 弹栈遵循先进后出，后进先出的数据结构原则
        try {
            stack.pop();      // 弹栈java.lang.Object@4b67cf4d元素成功栈帧指向8
            stack.pop();      // 弹栈java.lang.Object@2503dbd3元素成功栈帧指向7
            stack.pop();      // 弹栈java.lang.Object@330bedb4元素成功栈帧指向6
            stack.pop();      // 弹栈java.lang.Object@45ee12a7元素成功栈帧指向5
            stack.pop();      // 弹栈java.lang.Object@135fbaa4元素成功栈帧指向4
            stack.pop();      // 弹栈java.lang.Object@6d6f6e28元素成功栈帧指向3
            stack.pop();      // 弹栈java.lang.Object@7f31245a元素成功栈帧指向2
            stack.pop();      // 弹栈java.lang.Object@14ae5a5元素成功栈帧指向1
            stack.pop();      // 弹栈java.lang.Object@677327b6元素成功栈帧指向0
            stack.pop();      // 弹栈java.lang.Object@1540e19d元素成功栈帧指向-1
            stack.pop();      // 弹栈失败，栈已空！
        } catch (MyStackOperationException e){
            System.out.println(e.getMessage());
        }

    }
}

class MyStack {
    // 向栈当中存储元素，我们这里使用一维数组模拟。存到栈中,就表示存储到数组中
    // 为什么选择Object类型数组？因为这个栈可以存储Java中任何引用类型的数据
    // 还记得前面我们学的Object类吗？每个类都是继承了Object类的，包括String类也是可以存储进去。Object类就是个超级父类。
    private Object[] elements; // 表示存储的压栈元素

    // 栈帧，永远指向顶部元素
    // 那么这个默认初始值应该是多少。注意：最初的栈是空的，一个元素都没有
    private int index;  // 表示栈帧

    public MyStack() {
        // 一维数组动态初始化，默认初始化容量是10.
        this.elements = new Object[10];
        // 给index初始化
        this.index = -1; // 想象一下，当下标为0时指向的是压在栈底部的第一个元素。但是最初的栈是空的，所以栈帧的初始值应该是-1
    }

    public void push(Object obj) throws MyStackOperationException {
        if (index >= elements.length-1){
            throw new MyStackOperationException("压栈失败，栈已满！");
        }
        // 程序能走到这里，说明栈没满
        // 向栈中加1个元素，栈帧向上移动一个位置
        elements[++index] = obj;
        // 再声明一次，所有System.out.println()方法执行时，如果输出引用的话，自动调用toString()方法
        System.out.println("压栈" + obj +"成功，栈帧指向" + index);
    }

    public void pop() throws MyStackOperationException {
        if (index < 0){
            throw new MyStackOperationException("弹栈失败，栈已空！");
        }
        // 程序能执行到这里，说明栈没有空
        System.out.print("弹栈" + elements[index] + "元素成功");
        // 栈帧向下移动一位
        index--;
        System.out.println("栈帧指向" + index);
    }

    // set和get也许用不上，但是必须写上，这是乌龟的屁股--龟腚(规定)，你使用IDEA生成就行了。
    // 封装：第一步：属性私有化。第二步：对外提供set和get方法
    // 这里的set和get方法都没用到，但是要养成习惯。
    public Object[] getElements() {
        return elements;
    }
    public void setElements(Object[] elements) {
        this.elements = elements;
    }
}

// 栈操作异常：自定义异常
class MyStackOperationException extends Exception{   // 编译时异常
    public MyStackOperationException() {} // 无参构造方法

    public MyStackOperationException(String s){
        super(s);
    } // 有参构造方法
}
