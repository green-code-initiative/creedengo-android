#!/bin/bash
# Add @DeprecatedRuleKey for old EC keys on Java rules
for f in $(find android-plugin/src/main/java/io/creedengo/java -name "*.java" -exec grep -l '@Rule(key = "GCI' {} \;); do
  num=$(grep '@Rule(key = "GCI' "$f" | sed 's/.*GCI\([0-9]*\).*/\1/')
  if grep -q '@DeprecatedRuleKey' "$f"; then
    sed -i '' "/@DeprecatedRuleKey(repositoryKey = \"ecocode-android-java\"/i\\
@DeprecatedRuleKey(repositoryKey = \"creedengo-android-java\", ruleKey = \"EC${num}\")
" "$f"
  else
    sed -i '' "/@Rule(key = \"GCI${num}\"/i\\
@DeprecatedRuleKey(repositoryKey = \"creedengo-android-java\", ruleKey = \"EC${num}\")
" "$f"
  fi
done

# Add @DeprecatedRuleKey for old EC keys on XML rules
for f in $(find android-plugin/src/main/java/io/creedengo/xml -name "*.java" -exec grep -l '@Rule(key = "GCI' {} \;); do
  num=$(grep '@Rule(key = "GCI' "$f" | sed 's/.*GCI\([0-9]*\).*/\1/')
  if grep -q '@DeprecatedRuleKey' "$f"; then
    sed -i '' "/@DeprecatedRuleKey(repositoryKey = \"ecocode-android-xml\"/i\\
@DeprecatedRuleKey(repositoryKey = \"creedengo-android-xml\", ruleKey = \"EC${num}\")
" "$f"
  else
    sed -i '' "/@Rule(key = \"GCI${num}\"/i\\
@DeprecatedRuleKey(repositoryKey = \"creedengo-android-xml\", ruleKey = \"EC${num}\")
" "$f"
  fi
done
