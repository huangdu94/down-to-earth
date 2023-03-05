# 导入整个模块
import function
# 导入指定函数
from function import add
# 使用as给函数指定别名
from function import add as function_alias
# 使用as给模块指定别名
import function as module_alias

# 导入所有函数（最好不要使用）
# from function import *

function.add(1, 2)
function.subtract(1, 2)
function.multiply(1, 2)
function.division(1, 2)

add(1, 2)

function_alias(1, 2)

module_alias.add(1, 2)
module_alias.subtract(1, 2)
module_alias.multiply(1, 2)
module_alias.division(1, 2)
