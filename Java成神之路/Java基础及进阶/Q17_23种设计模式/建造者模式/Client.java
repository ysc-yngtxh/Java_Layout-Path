package Q17_23种设计模式.建造者模式;

// 1. 产品类
class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    
    // 构造函数设为private，强制使用建造者
    private Computer() {}
    
    // 省略getter方法
    
    // 2. 建造者类
    public static class Builder {
        private Computer computer = new Computer();
        
        public Builder withCpu(String cpu) {
            computer.cpu = cpu;
            return this;
        }
        
        public Builder withRam(String ram) {
            computer.ram = ram;
            return this;
        }
        
        public Builder withStorage(String storage) {
            computer.storage = storage;
            return this;
        }
        
        public Builder withGpu(String gpu) {
            computer.gpu = gpu;
            return this;
        }
        
        public Computer build() {
            // 可以在这里添加校验逻辑
            if (computer.cpu == null) {
                throw new IllegalStateException("CPU is required");
            }
            return computer;
        }
    }
}

// 3. 使用方式
public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
            .withCpu("Intel i7")
            .withRam("16GB")
            .withStorage("512GB SSD")
            .withGpu("NVIDIA RTX 3080")
            .build();
    }
}
