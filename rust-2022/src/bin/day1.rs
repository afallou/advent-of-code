use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    println!("Hello, world!");

    let mut elf_calories: Vec<i32> = Vec::new();

    let mut current_count: i32 = 0;

    let lines = read_lines("./input/day1-input.txt");

    for line in lines {
        if line.trim().is_empty() {
            println!("Next elf carries {}", current_count);
            elf_calories.push(current_count);
            current_count = 0;
        } else {
            current_count += line.parse::<i32>().unwrap();
        }
    }

    let max_value = elf_calories.iter().max();
    match max_value{
        Some(max_value) => println!("Max is {}", max_value),
        None => println!("Oops"),
    }

    const N_TOP: usize = 3;
    let mut top_n_values: [i32; N_TOP] = [0, 0, 0];
    for calorie_value in elf_calories {
        for top_index in [0, N_TOP] {
            if calorie_value >= top_n_values[top_index] {
                let new_slice = &top_n_values[top_index..];
                new_slice.rotate_right(1);
                new_slice[0] = calorie_value;
                top_n_values[top_index..] = new_slice;
            }
        }
    }


}

fn read_lines<P>(filename: P) -> Vec<String>
where P: AsRef<Path>, {
    let file = File::open(filename).expect("msg");
    io::BufReader::new(file)
        .lines()
        .map(|l| l.expect("Could not parse line"))
        .collect()
}
