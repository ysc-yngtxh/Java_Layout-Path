
// 计算文件的 MD5 校验值
function calculateMD5(fileChunk) {
    return new Promise((resolve, reject) => {
        // 构造文件块对象 Blob.代表一个不可变的、原始数据的类文件对象。
        const blob = new Blob([fileChunk]);
        // 获取字符流对象
        const reader = new FileReader();
        // `FileReader`用于将`Blob`读取为`ArrayBuffer`。`ArrayBuffer`表示文件块的原始二进制数据。
        reader.readAsArrayBuffer(blob);
        // reader.onload 是读取操作完成后的回调函数。当FileReader读取完毕后，reader.result 会包含一个 ArrayBuffer
        reader.onload = () => {
            // 将ArrayBuffer转换为WordArray对象
            const wordArray = CryptoJS.lib.WordArray.create(reader.result);
            // 计算MD5哈希值
            const md5 = CryptoJS.MD5(wordArray).toString();
            resolve(md5);
        };
        reader.onerror = () => {
            // 调用reject方法，Promise变为操作失败状态（rejected）
            reject(new Error('Failed to calculate hash'));
        };
    })
}