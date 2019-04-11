set AppName=%1
set user=%2
set pass=%3
cf login -u %user% -p %pass% -s development
start /W buildJar
cf push "%AppName%-dev" -p "target/%AppName%.jar" -f "dev-manifest.yml"
cf logout