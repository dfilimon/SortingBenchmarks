#! /usr/bin/env octave -q
1;

function drawPlots(prefix)
  colors = "rgbmck";  
  files = dir([prefix, ".*.benchmark"]); 
  
  clf;
  hold on;

  for i = 1 : length(files)
    printf("%s %s\n", files(i).name(), colors(i));
    fileHandle = fopen(files(i).name, "r");
    all = fscanf(fileHandle, "%d", 500);
    x = all(1 : 2 : end);
    y = all(2 : 2 : end);

    #p = polyfit(x, y, 2);    
    #plot(x, polyval(p, x), [colors(i), ""], "LineWidth", 1)
    plot(x, y, [colors(i), "x"], "MarkerSize", 2)

    xlabel("Number of elements");
    ylabel("Nanoseconds");
  end
  title([prefix, " benchmarks"]);
  legend(files.name)
  print([prefix, ".benchmarks.png"], "-dpng");

  hold off;
  pause();
end

# plotting benchmarks with random data
drawPlots("rand")

# plotting benchmarks with worstcase data
drawPlots("worstcase")
  