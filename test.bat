javac -encoding UTF-8 -Xlint:deprecation -Xlint:unchecked -d bin\ -sourcepath src\ src\harujisaku\minicode\MiniCode.java
if not %ERRORLEVEL% == 0 (
pause
exit /b 1
)
java -cp bin\ harujisaku.minicode.MiniCode