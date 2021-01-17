package work.huangdu.exploration.start_from_scratch.hashmap.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 609. 在系统中查找重复文件
 * 给定一个目录信息列表，包括目录路径，以及该目录中的所有包含内容的文件，您需要找到文件系统中的所有重复文件组的路径。一组重复的文件至少包括二个具有完全相同内容的文件。
 * 输入列表中的单个目录信息字符串的格式如下：
 * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
 * 这意味着有 n 个文件（f1.txt, f2.txt ... fn.txt 的内容分别是 f1_content, f2_content ... fn_content）在目录 root/d1/d2/.../dm 下。注意：n>=1 且 m>=0。如果 m=0，则表示该目录是根目录。
 * 该输出是重复文件路径组的列表。对于每个组，它包含具有相同内容的文件的所有文件路径。文件路径是具有下列格式的字符串：
 * "directory_path/file_name.txt"
 * 示例 1：
 * 输入：
 * ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
 * 输出：
 * [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 * 注：
 * 最终输出不需要顺序。
 * 您可以假设目录名、文件名和文件内容只有字母和数字，并且文件内容的长度在 [1，50] 的范围内。
 * 给定的文件数量在 [1，20000] 个范围内。
 * 您可以假设在同一目录中没有任何文件或目录共享相同的名称。
 * 您可以假设每个给定的目录信息代表一个唯一的目录。目录路径和文件信息用一个空格分隔。
 * 超越竞赛的后续行动：
 * 假设您有一个真正的文件系统，您将如何搜索文件？广度搜索还是宽度搜索？
 * 如果文件内容非常大（GB级别），您将如何修改您的解决方案？
 * 如果每次只能读取 1 kb 的文件，您将如何修改解决方案？
 * 修改后的解决方案的时间复杂度是多少？其中最耗时的部分和消耗内存的部分是什么？如何优化？
 * 如何确保您发现的重复文件不是误报？
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/6 20:38
 */
public class FindDuplicate {

    /**
     * 文件内容及该内容所对应的文件列表
     */
    private final Map<String, List<String>> map = new HashMap<>();

    public List<List<String>> findDuplicate(String[] paths) {
        for (String path : paths) {
            parsePath(path);
        }
        List<List<String>> result = new ArrayList<>(map.size());
        for (List<String> files : map.values()) {
            if (files.size() > 1) {
                result.add(files);
            }
        }
        return result;
    }

    private void parsePath(String path) {
        String[] data = path.split(" ");
        String fartherPath = data[0].concat("/");
        for (int i = 1, n = data.length; i < n; i++) {
            int index = data[i].indexOf('(');
            String content = data[i].substring(index + 1, data[i].length() - 1);
            List<String> fileList = map.computeIfAbsent(content, k -> new ArrayList<>());
            fileList.add(fartherPath.concat(data[i].substring(0, index)));
        }
    }
}
