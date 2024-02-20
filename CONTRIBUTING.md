# シンプルなToDoアプリ

## フォーマッターについて
[DeteKt](https://github.com/detekt/detekt)と`detekt-formatter`,[Twitter Jetpack Compose Rules](https://twitter.github.io/compose-rules/rules/)を併用した静的解析を実施しています。

[detekt(Intellij IDEs Plugin)](https://plugins.jetbrains.com/plugin/10761-detekt)を導入することにより、AndroidStudio内でエラーを確認することができます。

また、`./.git/hooks/pre-commit`に下記内容を記述することにより、コミット時に自動的にコードチェックが実行されます。
```shell
#!/usr/bin/env bash
echo "Running detekt check..."
OUTPUT="/tmp/detekt-$(date +%s)"
./gradlew detekt > $OUTPUT
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
  cat $OUTPUT
  rm $OUTPUT
  echo "***********************************************"
  echo "                 detekt failed                 "
  echo " Please fix the above issues before committing "
  echo "***********************************************"
  exit $EXIT_CODE
fi
rm $OUTPUT
```
