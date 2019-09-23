### git
***
1. 初始化  
	`git init`
2. 设置签名
	+ 项目级别/仓库级别  
	`git config user.name`  
	`git config user.email`
	+ 系统用户级别  
	`git config --global user.name`  
	`git config --global user.email`
3. 查看状态  
	`git status`
4. 暂存区
	+ 添加  
	`git add`     
	+ 删除  
	`git rm`
	+ 提交  
	`git commit`
5. 历史记录  
	`git log`  
	`git log --pretty=oneline`  
	`git reflog`
6. 前进和后退  
	`git reset --hard`
7. 分支管理
	+ 创建分支  
	`git branch`
	+ 切换分支  
	`git checkout`
	+ 查看分支  
	`git branch -v`
	+ 比较差异  
	`git diff`  
	`git log -p`
	+ 合并分支  
	`git merge`
	+ 冲突解决
		1. 编辑文件，删除特殊符号
		2. 把文件修改到满意的程度，保存退出
		3. `git add [文件名]`
		4. `git commit -m "日志信息"` (此时 commit 一定不能带具体文件名)
### github
***
1. 远程地址加别名  
	`git remote add [别名] [远程地址]`
2. 查看远程地址别名  
	`git remote -v`
3. 将本地库内容推送到github中  
	`git push [远程地址/别名] master`
4. 克隆github中内容到本地库  
	`git clone [远程地址/别名]`
5. 更新本地库从github  
	`git fetch` add `git merge`  
	or `git pull`
***